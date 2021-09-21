import org.junit.Assert;
import org.junit.Test;
import service.ClientService;
import service.DataValidator;

import java.util.HashMap;
import java.util.Map;

public class ClientServiceTest {


    @Test
    public void testPhoneValidation() {
        String name = "Bjorg";
        String login = "bobro1385";
        String password = "123lolo";
        String rePassword= "123lolo";
        String phone= "+380997644531";

        Map<String,Object> map;
        ClientService cs = new ClientService();
        map = cs.validateRegistration(login,password,rePassword,name,phone);
        Assert.assertTrue(map.containsKey("validated"));
    }

    @Test
    public void testPhoneValidationBadPassword() {
        String name = "Bjorg";
        String login = "bobro1385";
        String password = "123lo";
        String rePassword= "123lo";
        String phone= "+380997644531";

        Map<String,Object> map;
        ClientService cs = new ClientService();
        map = cs.validateRegistration(login,password,rePassword,name,phone);
        Assert.assertTrue(map.get("badPass").equals(true));
    }

    @Test
    public void testPhoneValidationPasswordsDontMatch() {
        String name = "Bjorg";
        String login = "bobro1385";
        String password = "123lolo";
        String rePassword= "123lola";
        String phone= "+380997644531";

        Map<String,Object> map;
        ClientService cs = new ClientService();
        map = cs.validateRegistration(login,password,rePassword,name,phone);
        Assert.assertTrue(map.get("diffPass").equals(true));
    }

    @Test
    public void testPhoneValidationName() {
        String name = "Bulbock";
        String login = "bobro1385";
        String password = "123lolo";
        String rePassword= "123lolo";
        String phone= "+380997644531";

        Map<String,Object> map;
        ClientService cs = new ClientService();
        map = cs.validateRegistration(login,password,rePassword,name,phone);
        Assert.assertTrue(map.get("validated").equals(true));
    }
}
