
import org.junit.Assert;
import org.junit.Test;
import service.DataValidator;

public class DataValidatorTest {

    String l1;
    String l2;
    String l3;
    String l4;


    @Test
    public void testLoginValidation() {
        l1 = "testos";
        l2 = "memo123";
        l3 = "Вырвваы12";
        l4 = "123123414";
        Assert.assertTrue(DataValidator.validateLogin(l1));
        Assert.assertTrue(DataValidator.validateLogin(l2));
        Assert.assertFalse(DataValidator.validateLogin(l3));
        Assert.assertTrue(DataValidator.validateLogin(l4));
    }

    @Test
    public void testPasswordValidation() {
        l1 = "testos1111";
        l2 = "me2";
        l3 = "Вырвваы12";
        l4 = "123123414ррр";
        Assert.assertTrue(DataValidator.validatePassword(l1));
        Assert.assertFalse(DataValidator.validatePassword(l2));
        Assert.assertFalse(DataValidator.validatePassword(l3));
        Assert.assertFalse(DataValidator.validatePassword(l4));
    }

    @Test
    public void testNameValidation() {
        l1 = " ";
        l2 = "memar1231";
        l3 = "Vu";
        l4 = "Bulbock";
        Assert.assertFalse(DataValidator.validateName(l1));
        Assert.assertFalse(DataValidator.validateName(l2));
        Assert.assertTrue(DataValidator.validateName(l3));
        Assert.assertTrue(DataValidator.validateName(l4));
    }

    @Test
    public void testPhoneValidation() {
        l1 = "+380554311231";
        l2 = "380887766345";
        l3 = "+380123123";
        l4 = "sadasd ііі";
        Assert.assertTrue(DataValidator.validatePhone(l1));
        Assert.assertFalse(DataValidator.validatePhone(l2));
        Assert.assertFalse(DataValidator.validatePhone(l3));
        Assert.assertFalse(DataValidator.validatePhone(l4));
    }


}
