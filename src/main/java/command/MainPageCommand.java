package command;

import constants.PageConstants;
import dao.Impl.ProductDAOImpl;
import entity.Product;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPageCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        List<Product> products = null;
        try {
            products = productDAO.getAll();
        } catch (FailedDAOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("products",products);
        map.put("page", PageConstants.MAIN_PAGE);
        return map;
    }
}
