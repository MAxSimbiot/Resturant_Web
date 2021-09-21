package command.commands;

import constants.DAOConstants;
import constants.PageConstants;
import dao.Impl.ClientDAOImpl;
import entity.Client;
import entity.Role;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand implements Command {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        Map<String, Object> map = new HashMap<>();
        Client client;
        if (login != null && password != null) {
            client = ClientService.getClientByLoginPassword(login,password);
        } else {
            logger.log(Level.INFO,"Incomplete login");
            map.put(PageConstants.PAGE, PageConstants.LOGIN_PAGE);
            return map;
        }

        if (client != null) {
            map = initSession(request, client);
        } else {
            logger.log(Level.INFO,"No such client " + login + " " + password);
            map.put(PageConstants.PAGE, PageConstants.LOGIN_PAGE);
            request.setAttribute("loginErrorMsg", true);
        }
        return map;
    }



    private Map<String, Object> initSession(HttpServletRequest request, Client client) {
        Map<String, Object> map = new HashMap<>();
        Role role = client.getRoleEntity();

        if(role.equals(Role.CLIENT)){
            map.put(PageConstants.PAGE, PageConstants.COMMAND_MAIN_PAGE);
            logger.log(Level.INFO,"Logged client " + client.getName());
        }else if(role.equals(Role.MANAGER)){
            map.put(PageConstants.PAGE,PageConstants.COMMAND_MANAGER_PAGE);
            logger.log(Level.INFO,"Logged manager " + client.getName());
        }else if(role.equals(Role.ADMIN)){
            map.put(PageConstants.PAGE,PageConstants.COMMAND_ADMIN_PAGE);
            logger.log(Level.INFO,"Logged admin " + client.getName());
        }else {
            map.put(PageConstants.PAGE,PageConstants.LOGIN_PAGE);
            logger.log(Level.INFO,"Didn`t login " + client.getName());
            return map;
        }


        HttpSession session = request.getSession(true);

        session.setMaxInactiveInterval(60 * 60);
        session.setAttribute("logged", true);
        session.setAttribute(DAOConstants.CLIENT, client);
        session.setAttribute("role", role);
        return map;
    }
}
