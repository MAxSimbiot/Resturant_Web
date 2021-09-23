package service;

import entity.Product;

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

}
