package dao.Impl;

import constants.DAOConstants;
import dao.AbstractDAO;
import dao.DBManager;
import entity.Category;
import entity.Product;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements AbstractDAO {

    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class);

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product;";
    @Override
    public List getAll() throws FailedDAOException {
        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try{
            connection = DBManager.getInstance().getConnection();
            executeGetAll(connection,products);


        }catch (SQLException ex){
            DBManager.getInstance().closeConnection(connection);
            logger.log(Level.ERROR, "Can`t get products!", ex);
            throw new FailedDAOException("Can`t get products!");
        }finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return products;
    }

    private void executeGetAll(Connection connection, List<Product> products) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_PRODUCTS);
        while (resultSet.next()){
               products.add(initProduct(resultSet));
        }
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
        return product;
    }

    @Override
    public boolean update(Object entity) throws FailedDAOException {
        throw new UnsupportedOperationException("Deletion of account not supported yet");
    }

    @Override
    public boolean delete(Object o) throws FailedDAOException {
        throw new UnsupportedOperationException("Deletion of account not supported yet");
    }

    @Override
    public boolean create(Object entity) throws FailedDAOException {
        throw new UnsupportedOperationException("Deletion of account not supported yet");
    }


}
