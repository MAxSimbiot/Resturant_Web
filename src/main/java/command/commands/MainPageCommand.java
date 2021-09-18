package command.commands;

import constants.PageConstants;
import dao.Impl.ProductDAOImpl;
import entity.Product;
import exception.FailedDAOException;
import service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


public class MainPageCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        ProductService ps = new ProductService();
        List<Product> products = null;
        try {
            products = productDAO.getAll();
        } catch (FailedDAOException e) {
            e.printStackTrace();
        }
     if(products!=null) {
         String sortPrice = request.getParameter("sortPrice");
         if(sortPrice!=null){
             products = ps.sortPrice(products,sortPrice);
         }
         String search = request.getParameter("search");
         String sort = request.getParameter("sort");
         if (Boolean.parseBoolean(search)) {
             String query = request.getParameter("searchQuery");
             String locale = request.getSession().getAttribute("locale").toString();
             if (query != null && !query.isEmpty()) {
                 products = ps.searchInProductName(query, products);
             }
         } else if (sort != null) {
             products = ps.searchByCategory(Integer.parseInt(sort), products);
         }

     }
        Map<String, Object> map = new HashMap<>();
        map.put("products", products);
        map.put("page", PageConstants.MAIN_PAGE);
        return map;
    }


}