package repository;

import dao.Impl.ProductDAOImpl;
import entity.Product;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.List;


public class ProductRepositody {
    private static final Logger logger = LogManager.getLogger(ProductRepositody.class);

    public List<Product> getALlProducts() {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        List<Product> products = null;
        try {
            products = productDAO.getAll();
        } catch (FailedDAOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return products;
    }

}
