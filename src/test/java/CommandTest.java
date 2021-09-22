import command.commands.GoToLoginCommand;
import command.commands.LoginCommand;
import constants.PageConstants;
import dao.ClientDAO;
import dao.DBManager;
import dao.Impl.ClientDAOImpl;
import exception.FailedDAOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.ClientRepository;
import service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandTest {

    HttpServletResponse response;
    HttpServletRequest request;
    LoginCommand loginCommand;
    ClientRepository clientRepository;

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        clientRepository = mock(ClientRepository.class);
        when(clientRepository.getClientByLoginPassword(anyString(),anyString())).thenReturn(null);

        loginCommand = new LoginCommand();

        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");

    }

    @Test
    public void loginTest() throws FailedDAOException, SQLException {
        Map<String,Object> map = loginCommand.execute(request,response);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        response = null;
        request = null;
        loginCommand = null;
        clientRepository = null;
    }
}
