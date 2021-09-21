import command.commands.ChangeLanguageCommand;
import command.commands.ErrorCommand;
import constants.PageConstants;
import exception.FailedDAOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChangeLanduageCommandTest {
    HttpServletResponse response;
    HttpServletRequest request;
    HttpSession session;
    ChangeLanguageCommand languageCommand;
    Map<String, Object> map;
    String locale;


    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        languageCommand = new ChangeLanguageCommand();
        session = mock(HttpSession.class);
        locale = "ru";
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("locale")).thenReturn(locale);
        request.setAttribute("locale",locale);
    }

    @Test
    public void goToLoginTest() throws FailedDAOException {
        map = languageCommand.execute(request, response);
        Assert.assertEquals(map.get("page"), (PageConstants.COMMAND_MAIN_PAGE));
        Assert.assertEquals(locale,request.getSession().getAttribute("locale"));
    }

    @After
    public void tearDown() throws Exception {
        response = null;
        request = null;
        languageCommand = null;
        session = null;
        map = null;
        locale = null;
    }
}
