
import entity.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductSortingTest {

    List<Product> products;
    ProductService ps;

    @Before
    public void setUp() {
        ps = new ProductService();
        products = new ArrayList<>();
    }

    @Test
    public void testSortPriceCheap() {
        Product a = new Product();
        a.setPrice(1);
        Product b = new Product();
        b.setPrice(2);
        Product c = new Product();
        c.setPrice(3);

        products.add(a);
        products.add(b);
        products.add(c);

        List<Product> productsRes = products;

        String sort = "cheap";

        products = ps.sortPrice(products, sort);

        Assert.assertEquals(products, productsRes);
    }

    @Test
    public void testSortPriceExpensive() {
        Product a = new Product();
        a.setPrice(3);
        Product b = new Product();
        b.setPrice(2);
        Product c = new Product();
        c.setPrice(1);

        products.add(a);
        products.add(b);
        products.add(c);

        List<Product> productsRes = products;

        String sort = "expensive";

        products = ps.sortPrice(products, sort);

        Assert.assertEquals(products, productsRes);
    }

    @After
    public void tearDown() {
        ps = null;
        products = null;
    }
}
