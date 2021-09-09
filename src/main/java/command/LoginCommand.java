package command;

import constants.DAOConstants;
import constants.PageConstants;
import dao.Impl.ClientDAOImpl;
import entity.Client;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        Map<String, Object> map = new HashMap<>();

        ClientDAOImpl clientDAO = new ClientDAOImpl();
        Client client = null;
        if (login != null && password != null) {
            try {
                client = clientDAO.getClientByLoginAndPassword(login, password);
            } catch (FailedDAOException e) {
                e.printStackTrace();
            }
        }else{
            map.put(PageConstants.PAGE, PageConstants.LOGIN_PAGE);
            request.setAttribute("errorMsg","Enter both login and password!");
            return map;
        }
        if (client != null) {
             map = initSession(request, client);
        }else{
            map.put(PageConstants.PAGE, PageConstants.LOGIN_PAGE);
            request.setAttribute("errorMsg","Client not found!");
        }
        return map;
    }

    private Map<String,Object> initSession(HttpServletRequest request, Client client) {
        Map<String, Object> map = new HashMap<>();
        map.put(PageConstants.PAGE, PageConstants.COMMAND_MAIN_PAGE);
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(-1);
        session.setAttribute("logged",true);
        session.setAttribute(DAOConstants.CLIENT,client);
        session.setAttribute("role",client.getRoleEntity().toString());
        return map;
    }
}
