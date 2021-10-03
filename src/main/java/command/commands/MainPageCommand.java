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
        final String page = request.getParameter("page");
        final String pageNumber = request.getParameter("pageNumber");
        final int productsByPage = 6;
        final int defaultPage = 1;

        products = repository.getALlProducts();

        Map<String, Object> map = new HashMap<>();
        if (products != null && category != null) {
            products = ProductService.getProductsByCategoryId(products, Integer.parseInt(category));
            map.put("category", category);
        }

        if (products != null && query != null && !query.isEmpty()) {
            products = ProductService.getProductsByNameQuery(products, query);
            map.put("searchQuery", query);
        }
        if (products != null && sortPrice != null) {
            products = ProductService.sortPrice(products, sortPrice);
            map.put("sortPrice", sortPrice);
        }
        if (products != null) {
            int pageNum = defaultPage;
            if (pageNumber != null) {
                pageNum = Integer.parseInt(pageNumber);
            }
            if (page != null) {
                switch (page) {
                    case "next": {
                        pageNum++;
                        break;
                    }
                    case "prev": {
                        pageNum--;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
            if (pageNum <= 0||(pageNum * productsByPage - productsByPage)>=products.size()) {
                pageNum = defaultPage;
            }
            products = ProductService.showRange(productsByPage, pageNum, products);
            map.put("pageNumber", pageNum);
        }


        map.put("products", products);
        map.put("page", PageConstants.MAIN_PAGE);
        return map;
    }


}
