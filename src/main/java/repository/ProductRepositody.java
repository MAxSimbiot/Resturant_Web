package repository;

import entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepositody {



//    public List<Product> getProductsFromExpensive(String sortPrice){
//        if(!products.isEmpty()&&!sortPrice.isEmpty()){
//            if(sortPrice.equals("cheap")){
//                products = products.stream()
//                        .sorted(Comparator.comparingInt(Product::getPrice))
//                        .collect(Collectors.toList());
//            }else if(sortPrice.equals("expensive")){
//                products = products.stream()
//                        .sorted(Comparator.comparingInt(Product::getPrice).reversed())
//                        .collect(Collectors.toList());
//            }
//        }
//        return products;
//    }
//
//    public List<Product> getProductsFromCheap(String sortPrice){
//        if(!products.isEmpty()&&!sortPrice.isEmpty()){
//            if(sortPrice.equals("cheap")){
//                products = products.stream()
//                        .sorted(Comparator.comparingInt(Product::getPrice))
//                        .collect(Collectors.toList());
//            }else if(sortPrice.equals("expensive")){
//                products = products.stream()
//                        .sorted(Comparator.comparingInt(Product::getPrice).reversed())
//                        .collect(Collectors.toList());
//            }
//        }
//        return products;
//    }
//
//    public List<Product> searchByCategory(int categoryId) {
//        List<Product> result = new ArrayList<>();
//        for (Product p : products) {
//            if (p.getCategory_id() == categoryId) {
//                result.add(p);
//            }
//        }
//        return result;
//    }

    public List<Product> getProductsByNameQuery(String query, List<Product> products) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }
}
