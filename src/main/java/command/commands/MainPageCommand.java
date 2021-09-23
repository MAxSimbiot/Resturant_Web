package command.commands;

import constants.PageConstants;
import dao.Impl.ProductDAOImpl;
import entity.Product;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.ProductRepositody;
import service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


public class MainPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MainPageCommand.class);

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {

        ProductRepositody repository = new ProductRepositody();
        List<Product> products = null;

        final String sortPrice = request.getParameter("sortPrice");
        final String category = request.getParameter("category");
        final String query = request.getParameter("searchQuery");

        products = repository.getALlProducts();

        Map<String, Object> map = new HashMap<>();
        if (products!= null && category != null) {
            products = ProductService.getProductsByCategoryId(products,Integer.parseInt(category));
            map.put("category",category);
        }

        if (products!=null && query != null && !query.isEmpty()) {
            products = ProductService.getProductsByNameQuery(products,query);
            map.put("searchQuery",query);
        }
        if (products != null && sortPrice != null) {
            products = ProductService.sortPrice(products, sortPrice);
            map.put("sortPrice",sortPrice);
        }


        map.put("products", products);
        map.put("page", PageConstants.MAIN_PAGE);
        return map;
    }


}
