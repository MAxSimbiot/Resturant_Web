package dao.Impl;

import constants.DAOConstants;
import dao.DBManager;
import dao.ReceiptDAO;
import entity.Client;
import entity.Product;
import entity.Receipt;
import entity.Status;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO {
    private static final Logger logger = LogManager.getLogger(ReceiptDAOImpl.class);

//    private static final  String GET_RECEIPT_BY_ACC_ID = "SELECT * FROM product , category, receipt_has_product, receipt " +
//            "WHERE client_id = ? " +
//            "AND receipt.id = receipt_id " +
//            "AND product.id = receipt_has_product.product_id " +
//            "AND category_id = category.id " +
//            ";";

    private static final String GET_PRODUCTS_BY_RECEIPT_ID =
            "SELECT product.id, name_ru,name_us,description_ru,description_us,price,image_url,category_id, count FROM product ,receipt_has_product, receipt " +
                    "WHERE receipt_id = ? " +
                    "AND receipt.id = receipt_id " +
                    "AND product.id = receipt_has_product.product_id;";

    private static final String GET_RECEIPT_BY_ACC_ID =
            "SELECT id, status_id ,creation_time, last_update, client_id " +
                    "FROM receipt " +
                    "WHERE client_id = ?;";

    private static final String INSERT_PRODUCT_BY_ID = "INSERT INTO receipt_has_product (receipt_id,product_id,count) " +
            "VALUES (?,?,?) " +
            "ON DUPLICATE KEY UPDATE count = count + 1;";

    public Receipt getReceiptByAccountId(int accountId) throws FailedDAOException {
        Connection connection = null;
        Receipt receipt = null;
        try {
            connection = DBManager.getInstance().getConnection();
            receipt = executeGetByAccId(connection, receipt, accountId);
            receipt.setProducts(getAndFillProducts(connection, receipt));
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Unable to get receipt by acc id", ex);
            DBManager.getInstance().closeConnection(connection);
            throw new FailedDAOException("Unable to get receipt by acc id");
        } finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return receipt;
    }

    private Receipt executeGetByAccId(Connection connection, Receipt receipt, int accountId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_RECEIPT_BY_ACC_ID);
        ps.setInt(1, accountId);
        ResultSet resultSet = ps.executeQuery();
        receipt = initReceipt(resultSet);
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
        if (rs.next()) {
            Receipt receipt = new Receipt();
            receipt.setId(rs.getInt("id"));
            receipt.setClientId(rs.getInt("client_id"));

            int statusId = rs.getInt("status_id");
            Status status = Status.getByid(statusId);
            receipt.setStatusId(statusId);
            receipt.setStatusEntity(status);

            receipt.setCreationTime(rs.getDate("creation_time").toLocalDate());
            receipt.setLastUpdate(rs.getDate("last_update").toLocalDate());

            return receipt;
        }
        return null;
    }

    private Product initProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(DAOConstants.ID));
        product.setName_ru(resultSet.getString(DAOConstants.NAME_RU));
        product.setName_us(resultSet.getString(DAOConstants.NAME_US));
        product.setDescription_ru(resultSet.getString(DAOConstants.DESCRIPTION_RU));
        product.setDescription_us(resultSet.getString(DAOConstants.DESCRIPTION_US));
        product.setPrice(resultSet.getInt(DAOConstants.PRICE));
        product.setImage_url(resultSet.getString(DAOConstants.IMAGE_URL));
        int categoryId = resultSet.getInt(DAOConstants.CATEGORY_ID);
        product.setCategory_id(categoryId);
        product.setCount(resultSet.getInt("count"));
        System.out.println("1 product enit, " + product);
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
        boolean res = ps.executeUpdate() == 1;
        ps.close();
        return res;
    }

    @Override
    public List getAll() throws FailedDAOException {
        return null;
    }

    @Override
    public boolean update(Object entity) throws FailedDAOException {
        return false;
    }

    @Override
    public boolean delete(Object o) throws FailedDAOException {
        return false;
    }

    @Override
    public boolean create(Object entity) throws FailedDAOException {
        return false;
    }

    @Override
    public Object getByid(Object o) throws FailedDAOException {
        return null;
    }
}
