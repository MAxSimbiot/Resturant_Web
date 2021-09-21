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

public class UpdateClientCommand implements Command {
    private static final Logger logger = Logger.getLogger(UpdateClientCommand.class);
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        final Integer id = Integer.parseInt(request.getParameter("id"));
        final String login = request.getParameter("login");
        final String name = request.getParameter("name");
        final String password = request.getParameter("password");
        final String repPassword = request.getParameter("repeatPassword");
        final String phone = request.getParameter("phone");
        ClientService cs = new ClientService();
        boolean success = false;
         map = cs.validateClientInfo(login, password, repPassword, name, phone);

        if (map.containsKey("validated")) {
            ClientDAOImpl clientDAO = new ClientDAOImpl();
            map = new HashMap<>();
            Client c = ClientService.initClient(id,login, password, name, phone);
            try {
                success = clientDAO.update(c);
            } catch (FailedDAOException e) {
                map.put("errorMsg", true);
                logger.log(Level.ERROR,e);
            }
        }
        if(success){
            map.put("updateSuccessMsg",true);
            Client client = ClientService.getClientByLoginPassword(login,password);
            request.getSession().setAttribute(DAOConstants.CLIENT, client);
            System.out.println(request.getSession().getAttribute(DAOConstants.CLIENT));
        }else{
            map.put("updateErrorMsg", true);
        }

        map.put(PageConstants.PAGE,PageConstants.COMMAND_CLIENT_PAGE);
        return map;
    }
}
