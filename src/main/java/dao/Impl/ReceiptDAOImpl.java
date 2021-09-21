package dao.Impl;

import constants.DAOConstants;
import dao.DBManager;
import dao.ReceiptDAO;
import entity.Product;
import entity.Receipt;
import entity.Status;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO<Integer> {
    private static final Logger logger = LogManager.getLogger(ReceiptDAOImpl.class);

    private static final String GET_PRODUCTS_BY_RECEIPT_ID =
            "SELECT product.id, name,description,price,image_url,category_id, SUM(count) as count " +
                    "FROM product ,receipt_has_product, receipt " +
                    "WHERE receipt_id = ? " +
                    "AND receipt.id = receipt_id " +
                    "AND product.id = receipt_has_product.product_id " +
                    "GROUP BY product.id;";

    private static final String GET_RECEIPT_BY_ACC_ID =
            "SELECT receipt.id, status_id ,creation_time, last_update, client_id," +
                    " status.id as sid, status.name as sn, status.description as sd " +
                    "FROM receipt, status " +
                    "WHERE client_id = ? and status_id = status.id and status_id < 7;";

    private static final String INSERT_PRODUCT_BY_ID =
            "INSERT INTO receipt_has_product (receipt_id,product_id,count) " +
                    "VALUES (?,?,?) " +
                    ";";

    private static final String DECREMENT_PRODUCT_COUNT_BY_RECEIPT_AND_ID =
            "UPDATE receipt_has_product " +
                    "SET count = count - 1 " +
                    "WHERE receipt_id = ?  AND product_id = ? " +
                    "LIMIT 1;";

    private static final String DELETE_ZERO_COUNT_PRODUCTS =
            "DELETE FROM receipt_has_product " +
                    "WHERE count = 0;";

    private static final String DELETE_RECEIPT_BY_ID =
            "Delete from receipt " +
                    "where id = ?;";

    private static final String INSERT_NEW_RECEIPT =
            "INSERT INTO receipt(client_id,status_id) " +
                    "VALUES (?,?);";

    private static final String UPDATE_STATUS_BY_ID =
            "UPDATE receipt SET status_id = ? WHERE id = ?;";

    private static final String GET_ALL_RECEIPTS =
            "SELECT receipt.id, status_id ,creation_time, last_update, client_id, " +
            "status.id as sid, status.name as sn, status.description as sd " +
            "FROM receipt, status " +
            "WHERE status_id = status.id;";

    private static final String GET_ALL_RECEIPTS_BY_ID =
            "SELECT receipt.id, status_id ,creation_time, last_update, client_id, " +
            "status.id as sid, status.name as sn, status.description as sd " +
            "FROM receipt, status " +
            "WHERE status_id = status.id AND client_id = ?;";

    public Receipt getReceiptByAccountId(int accountId) throws FailedDAOException {
        Connection connection = null;
        Receipt receipt = null;
        try {
            connection = DBManager.getInstance().getConnection();
            receipt = executeGetByAccId(connection, receipt, accountId);
            if (receipt != null) {
                receipt.setProducts(getAndFillProducts(connection, receipt));
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Unable to get receipt by acc id", ex);
            DBManager.getInstance().closeConnection(connection);
            throw new FailedDAOException("Unable to get receipt by acc id");
        } finally {
            DBManager.getInstance().closeConnection(connection);
        }
        System.out.println(receipt);
        return receipt;
    }

    public boolean updateStatusById(int statusId,int receiptId) throws FailedDAOException {
        Connection connection = null;
        boolean result = false;
        try {
            connection = DBManager.getInstance().getConnection();
            result = executeUpdateStatusById(connection, statusId ,receiptId);
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Unable to update receipt status by id", ex);
            DBManager.getInstance().rollbackAndClose(connection);
            throw new FailedDAOException("Unable to update receipt status by id");
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return result;
    }

    private boolean executeUpdateStatusById(Connection connection,int statusId, int receiptId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS_BY_ID);
        ps.setInt(1,statusId);
        ps.setInt(2,receiptId);
        int rowsUpdated = ps.executeUpdate();
        ps.close();
        return rowsUpdated == 1;
    }


    public boolean deleteProductById(int receiptId, int productId) throws FailedDAOException {
        Connection connection = null;
        int rowsUpdated = 0;
        try {
            connection = DBManager.getInstance().getConnection();
            rowsUpdated = executeDeleteProductById(connection, receiptId, productId);
            int rowsDeleted = executeDeleteZeroCountProducts(connection);
            logger.log(Level.INFO, "Deleted zero count products: " + rowsDeleted);
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Unable to delete product by id", ex);
            DBManager.getInstance().rollbackAndClose(connection);
            throw new FailedDAOException("Unable to delete product by id");
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return rowsUpdated > 0;
    }

    private int executeDeleteProductById(Connection connection, int receiptId, int productId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DECREMENT_PRODUCT_COUNT_BY_RECEIPT_AND_ID);
        ps.setInt(1, receiptId);
        ps.setInt(2, productId);
        int rowsUpdated = ps.executeUpdate();
        ps.close();
        return rowsUpdated;
    }

    private int executeDeleteZeroCountProducts(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        int rowsUpdated = st.executeUpdate(DELETE_ZERO_COUNT_PRODUCTS);
        st.close();
        return rowsUpdated;
    }

    private Receipt executeGetByAccId(Connection connection, Receipt receipt, int accountId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_RECEIPT_BY_ACC_ID);
        ps.setInt(1, accountId);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            receipt = initReceipt(resultSet); ///1111111111111111111111111111
        }
        ps.close();
        return receipt;
    }

    private List<Product> getAndFillProducts(Connection connection, Receipt receipt) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_PRODUCTS_BY_RECEIPT_ID);
        ps.setInt(1, receipt.getId());
        ResultSet resultSet = ps.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(initProduct(resultSet));
        }
        return products;
    }

    private Receipt initReceipt(ResultSet rs) throws SQLException {
            Receipt receipt = new Receipt();
            receipt.setId(rs.getInt("id"));
            receipt.setClientId(rs.getInt("client_id"));
            int statusId = rs.getInt("status_id");
            ////status-----------------
            int sId = rs.getInt("sid");
            String statusName = rs.getString("sn");
            String statusDescription = rs.getString("sd");

            Status status = new Status(sId, statusName, statusDescription);
            receipt.setStatusId(statusId);
            receipt.setStatusEntity(status);

            receipt.setCreationTime(rs.getDate("creation_time").toLocalDate());
            receipt.setLastUpdate(rs.getDate("last_update").toLocalDate());
            return receipt;
    }

    private Product initProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(DAOConstants.ID));
        product.setName(resultSet.getString(DAOConstants.NAME));
        product.setDescription(resultSet.getString(DAOConstants.DESCRIPTION));
        product.setPrice(resultSet.getInt(DAOConstants.PRICE));
        product.setImage_url(resultSet.getString(DAOConstants.IMAGE_URL));
        int categoryId = resultSet.getInt(DAOConstants.CATEGORY_ID);
        product.setCategory_id(categoryId);
        product.setCount(resultSet.getInt("count"));
        return product;
    }

    public boolean insertProductIntoReceipt(int receiptId, int productId, int quantity) throws FailedDAOException {
        Connection connection = null;
        if (productId == 0 || receiptId == 0 || quantity == 0) {
            return false;
        }
        boolean success;
        try {
            connection = DBManager.getInstance().getConnection();
            success = executeInsertProduct(receiptId, productId, quantity, connection);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.log(Level.ERROR, "Can`t add product to receipt", ex);
            throw new FailedDAOException("Can`t add product to receipt");
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return success;
    }

    private boolean executeInsertProduct(int receiptId, int productId, int quantity, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_BY_ID);
        ps.setInt(1, receiptId);
        ps.setInt(2, productId);
        ps.setInt(3, quantity);
        boolean res = ps.executeUpdate() > 0;
        ps.close();
        return res;
    }

    @Override
    public List getAll() throws FailedDAOException {
        Connection connection = null;
        List<Receipt> receipts = null;
        try {
            connection = DBManager.getInstance().getConnection();
            receipts = executeGetAllReceipts(connection);
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Can`t get all receipts!", ex);
            DBManager.getInstance().closeConnection(connection);
            throw new FailedDAOException("Can`t get all receipts!");
        } finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return receipts;
    }

    public List<Receipt> getAllClientReceiptsById(int clientId) throws FailedDAOException {
        Connection connection = null;
        List<Receipt> receipts = null;
        try {
            connection = DBManager.getInstance().getConnection();
            receipts = executeGetAllReceiptsById(connection,clientId);
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Can`t get all receipts by id!", ex);
            DBManager.getInstance().closeConnection(connection);
            throw new FailedDAOException("Can`t get all receipts by id!");
        } finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return receipts;
    }

    private List<Receipt> executeGetAllReceiptsById(Connection connection,int clientId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_ALL_RECEIPTS_BY_ID);
        ps.setInt(1,clientId);
        ResultSet resultSet = ps.executeQuery();
        List<Receipt> receipts = new ArrayList();
        while (resultSet.next()){
            Receipt receipt = initReceipt(resultSet);
            if (receipt != null) {
                receipt.setProducts(getAndFillProducts(connection, receipt));
                receipts.add(receipt);
            }
        }
        return receipts;
    }

    private List<Receipt> executeGetAllReceipts(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_RECEIPTS);
        List<Receipt> receipts = new ArrayList();
        while (resultSet.next()){
            Receipt receipt = initReceipt(resultSet);
            if (receipt != null) {
                receipt.setProducts(getAndFillProducts(connection, receipt));
                receipts.add(receipt);
            }
        }
        return receipts;
    }

    @Override
    public boolean update(Receipt receipt) throws FailedDAOException {
        throw new UnsupportedOperationException("Deletion of account not supported yet");
    }

    @Override
    public boolean delete(Integer id) throws FailedDAOException {
        int rowsUpdated = 0;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            rowsUpdated = executeDelete(connection, id);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.log(Level.ERROR, "Can`t delete a receipt", ex);
            throw new FailedDAOException("Can`t delete a receipt");
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return rowsUpdated > 0;
    }

    private int executeDelete(Connection connection, int id) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement ps = connection.prepareStatement(DELETE_RECEIPT_BY_ID);
        ps.setInt(1, id);
        rowsUpdated = ps.executeUpdate();
        ps.close();
        return rowsUpdated;
    }

    @Override
    public boolean create(Receipt receipt) throws FailedDAOException {
        if (receipt == null) {
            return false;
        }
        int rowsUpdated;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            rowsUpdated = executeCreate(connection, receipt);
        } catch (SQLException ex) {
            if (connection != null) {
                DBManager.getInstance().rollbackAndClose(connection);
            }
            logger.log(Level.ERROR, "Can`t delete a receipt", ex);
            throw new FailedDAOException("Can`t delete a receipt");
        } finally {
            if (connection != null) {
                DBManager.getInstance().commitAndClose(connection);
            }
        }
        return rowsUpdated > 0;
    }

    @Override
    public Receipt getById(Integer id) throws FailedDAOException {
        throw new UnsupportedOperationException("Get receipt by id not supported yet");
    }

    private int executeCreate(Connection connection, Receipt receipt) throws SQLException {
        int rowsUpdated;
        PreparedStatement ps = connection.prepareStatement(INSERT_NEW_RECEIPT);
        ps.setInt(1, receipt.getClientId());
        ps.setInt(2, receipt.getStatusId());
        rowsUpdated = ps.executeUpdate();
        ps.close();
        return rowsUpdated;
    }


}
