import command.commands.ErrorCommand;
import command.commands.LoginCommand;
import constants.PageConstants;
import dao.ClientDAO;
import exception.FailedDAOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.mockito.Mockito.mock;


public class ErrorCommandTest {

    HttpServletResponse response;
    HttpServletRequest request;
    ErrorCommand errorCommand;
    Map<String, Object> map;


    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        errorCommand = new ErrorCommand();
    }

    @Test
    public void goToLoginTest() throws FailedDAOException {
        map = errorCommand.execute(request, response);
        Assert.assertEquals(map.get("page"), (PageConstants.ERROR_PAGE));
        Assert.assertNotNull(map.get("errorMessage"));
    }

    @After
    public void tearDown() throws Exception {
        response = null;
        request = null;
        errorCommand = null;
        map = null;
    }
}