package command;

import constants.DAOConstants;
import constants.PageConstants;
import dao.Impl.ClientDAOImpl;
import entity.Client;
import exception.FailedDAOException;
import org.apache.log4j.Logger;
import service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RegisterCommand implements Command {

    private static final Logger logger = Logger.getLogger(RegisterCommand.class);
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        final String login = request.getParameter(DAOConstants.LOGIN);
        final String password = request.getParameter(DAOConstants.PASSWORD);
        final String repPassword = request.getParameter(DAOConstants.REP_PASSWORD);
        final String name = request.getParameter(DAOConstants.NAME);
        final String phone = request.getParameter(DAOConstants.PHONE);
        ClientService cs = new ClientService();
        boolean success = false;
        Map<String, Object> map = cs.validateRegistration(login, password, repPassword, name, phone);
        if (map.containsKey("validated")) {
            ClientDAOImpl clientDAO = new ClientDAOImpl();
            map = new HashMap<>();
            Client c = new Client();

            c.setLogin(login);
            c.setPassword(password);
            c.setName(name);
            c.setPhone(phone);

            try {
                success = clientDAO.create(c);
                System.out.println(clientDAO.getClientByLoginAndPassword(login, password));
            } catch (FailedDAOException e) {
                map.put("errorMsg", "User can`t be added");
            }
        }
        if (success) {
            map.put(PageConstants.PAGE, PageConstants.COMMAND_MAIN_PAGE);
        } else {
            map.put(PageConstants.PAGE, PageConstants.LOGIN_PAGE);
        }
        return map;
    }
}
