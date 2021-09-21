package command.commands;

import constants.DAOConstants;
import constants.PageConstants;
import dao.Impl.ClientDAOImpl;
import entity.Client;
import exception.FailedDAOException;
import org.apache.log4j.Level;
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
        Map<String, Object> map = cs.validateClientInfo(login, password, repPassword, name, phone);

        if (map.containsKey("validated")) {
            ClientDAOImpl clientDAO = new ClientDAOImpl();
            map = new HashMap<>();
            Client c = ClientService.initClient(login, password, name, phone);
            try {
                success = clientDAO.create(c);
            } catch (FailedDAOException e) {
                map.put("errorMsg", true);
                logger.log(Level.ERROR,e);
            }
        }
        if(success){
            map.put("registerSuccessMsg",true);
        }else{
            map.put("registerErrorMsg", true);
        }
        map.put(PageConstants.PAGE, PageConstants.LOGIN_PAGE);

        return map;
    }


}
