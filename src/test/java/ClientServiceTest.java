
import org.junit.Assert;
import org.junit.Test;
import service.ClientService;
import java.util.Map;


public class ClientServiceTest {

    String name;
    String login;
    String password;
    String rePassword;
    String phone;

    @Test
    public void testPhoneValidation() {
        name = "Bjorg";
        login = "bobro1385";
        password = "123lolo";
        rePassword= "123lolo";
        phone= "+380997644531";

        Map<String,Object> map;
        map = ClientService.validateClientInfo(login,password,rePassword,name,phone);
        Assert.assertTrue(map.containsKey("validated"));
    }

    @Test
    public void testPhoneValidationBadPassword() {
        name = "Bjorg";
        login = "bobro1385";
        password = "123lo";
        rePassword= "123lo";
        phone= "+380997644531";

        Map<String,Object> map;
        map = ClientService.validateClientInfo(login,password,rePassword,name,phone);
        Assert.assertEquals(true, map.get("badPass"));
    }

    @Test
    public void testPhoneValidationPasswordsDontMatch() {
        name = "Bjorg";
        login = "bobro1385";
        password = "123lolo";
        rePassword= "123lola";
        phone= "+380997644531";

        Map<String,Object> map;
        map = ClientService.validateClientInfo(login,password,rePassword,name,phone);
        Assert.assertEquals(true, map.get("diffPass"));
    }

    @Test
    public void testPhoneValidationName() {
        name = "Bulbock";
        login = "bobro1385";
        password = "123lolo";
        rePassword= "123lolo";
        phone= "+380997644531";

        Map<String,Object> map;
        map = ClientService.validateClientInfo(login,password,rePassword,name,phone);
        Assert.assertEquals(true, map.get("validated"));
    }
}
