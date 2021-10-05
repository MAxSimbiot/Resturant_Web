import entity.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class PaginationTest {

    List<Product> productList;

    @Before
    public void setUp(){
        productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
    }
    @Test
    public void testPaginationUtility(){
        Assert.assertTrue(ProductService.showRange(3,1,productList).size() == 3);
        Assert.assertTrue(ProductService.showRange(3,2,productList).size() == 2);
        Assert.assertTrue(ProductService.showRange(3,3,productList).size() == 5);
        Assert.assertTrue(ProductService.showRange(9,1,productList).size() == 5);
        Assert.assertTrue(ProductService.showRange(-1,-1,productList).size() == 5);
    }
    @After
    public void tearDown(){
        productList = null;
    }
}
