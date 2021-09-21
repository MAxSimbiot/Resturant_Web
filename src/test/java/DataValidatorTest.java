import command.commands.GoToLoginCommand;
import constants.PageConstants;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class DataValidatorTest {


    @Test
    public void testLoginValidation() {
        String l1 = "testos";
        String l2 = "memo123";
        String l3 = "Вырвваы12";
        String l4 = "123123414";
        Assert.assertTrue(DataValidator.validateLogin(l1));
        Assert.assertTrue(DataValidator.validateLogin(l2));
        Assert.assertFalse(DataValidator.validateLogin(l3));
        Assert.assertTrue(DataValidator.validateLogin(l4));
    }

    @Test
    public void testPasswordValidation() {
        String l1 = "testos1111";
        String l2 = "me2";
        String l3 = "Вырвваы12";
        String l4 = "123123414ррр";
        Assert.assertTrue(DataValidator.validatePassword(l1));
        Assert.assertFalse(DataValidator.validatePassword(l2));
        Assert.assertFalse(DataValidator.validatePassword(l3));
        Assert.assertFalse(DataValidator.validatePassword(l4));
    }

    @Test
    public void testNameValidation() {
        String l1 = " ";
        String l2 = "memar1231";
        String l3 = "Vu";
        String l4 = "Bulbock";
        Assert.assertFalse(DataValidator.validateName(l1));
        Assert.assertFalse(DataValidator.validateName(l2));
        Assert.assertTrue(DataValidator.validateName(l3));
        Assert.assertTrue(DataValidator.validateName(l4));
    }

    @Test
    public void testPhoneValidation() {
        String l1 = "+380554311231";
        String l2 = "380887766345";
        String l3 = "+380123123";
        String l4 = "sadasd ііі";
        Assert.assertTrue(DataValidator.validatePhone(l1));
        Assert.assertFalse(DataValidator.validatePhone(l2));
        Assert.assertFalse(DataValidator.validatePhone(l3));
        Assert.assertFalse(DataValidator.validatePhone(l4));
    }


}
