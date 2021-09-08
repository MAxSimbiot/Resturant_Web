package command;

import com.mysql.cj.Session;
import constants.PageConstants;
import dao.Impl.ClientDAOImpl;
import entity.Client;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand implements Command{
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        ClientDAOImpl clientDAO = new ClientDAOImpl();
        Client client = null;
        map.put("page","/MainServlet?command=mainPage");
        if(login == null||password == null){
            return map;
        }
        try {
             client = clientDAO.getClientByLoginAndPassword(login,password);
        } catch (FailedDAOException e) {
            e.printStackTrace();
        }
        System.out.println("Client " + client);
        if(client!= null){
            HttpSession session = request.getSession();
            session.setAttribute("client",client);
            }
        return map;
    }
}
