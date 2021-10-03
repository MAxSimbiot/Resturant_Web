package service;

import entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    public static List<Product> sortPrice(List<Product> products, String sortPrice) {
        if (!products.isEmpty() && !sortPrice.isEmpty()) {
            if (sortPrice.equals("cheap")) {
                products = products.stream()
                        .sorted(Comparator.comparingInt(Product::getPrice))
                        .collect(Collectors.toList());
            } else if (sortPrice.equals("expensive")) {
                products = products.stream()
                        .sorted(Comparator.comparingInt(Product::getPrice).reversed())
                        .collect(Collectors.toList());
            }
        }
        return products;
    }

    public static List<Product> getProductsByCategoryId(List<Product> products, int categoryId) {
        return products.stream()
                .filter(product -> product.getCategory_id() == categoryId)
                .collect(Collectors.toList());
    }

    public static List<Product> getProductsByNameQuery(List<Product> products, String query) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase().trim()))
                .collect(Collectors.toList());
    }

    public static List<Product> showRange(int productsByPage, int page, List<Product> products) {
        if (products.size() <= productsByPage || productsByPage <= 0 || page <= 0) {
            return products;
        }
        int start = 0;
        if (page > 1) {
            start = productsByPage * page - productsByPage;
        }
        int finish = start + productsByPage;
        if(start >= products.size()){
            return products;
        }
        List<Product> res = new ArrayList<>();
        for (int i = start; i < products.size() && i < finish; i++) {
            res.add(products.get(i));
        }
        return res;
    }

}
