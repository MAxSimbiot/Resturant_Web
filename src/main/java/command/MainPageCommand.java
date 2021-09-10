package command;

import constants.PageConstants;
import dao.Impl.ProductDAOImpl;
import entity.Product;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

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
     if(products!=null) {
         String sortPrice = request.getParameter("sortPrice");
         if(sortPrice!=null){
             products = sortPrice(products,sortPrice);
         }
         String search = request.getParameter("search");
         String sort = request.getParameter("sort");
         if (Boolean.parseBoolean(search)) {
             String query = request.getParameter("searchQuery");
             String locale = request.getSession().getAttribute("locale").toString();
             if (query != null && !query.isEmpty()) {
                 products = searchInProductName(query, locale, products);
             }
         } else if (sort != null) {
             products = searchByCategory(Integer.parseInt(sort), products);
         }

     }


        Map<String, Object> map = new HashMap<>();
        map.put("products", products);
        map.put("page", PageConstants.MAIN_PAGE);
        return map;
    }

    private List<Product> sortPrice(List<Product> products,String sortPrice){
        if(!products.isEmpty()&&!sortPrice.isEmpty()){
            if(sortPrice.equals("cheap")){
                products = products.stream()
                        .sorted(Comparator.comparingInt(Product::getPrice))
                        .collect(Collectors.toList());
            }else if(sortPrice.equals("expensive")){
                products = products.stream()
                        .sorted(Comparator.comparingInt(Product::getPrice).reversed())
                        .collect(Collectors.toList());
            }
        }
        return products;
    }

    private List<Product> searchByCategory(int categoryId,List<Product> products) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory_id() == categoryId) {
                result.add(p);
            }
        }
        return result;
    }

    private List<Product> searchInProductName(String query, String locale, List<Product> products) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (locale.equals("ru")) {
                if (p.getName_ru().toLowerCase().contains(query.toLowerCase())) {
                    result.add(p);
                }
            }else if(locale.equals("us")) {
                if (p.getName_us().toLowerCase().contains(query.toLowerCase())) {
                    result.add(p);
                }
            }
        }
        return result;
    }
}
