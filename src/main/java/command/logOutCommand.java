package command;

import com.mysql.cj.Session;
import constants.PageConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class logOutCommand implements Command{
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map  = new HashMap<>();
        map.put("page", "/MainServlet?command=mainPage");
        HttpSession session = request.getSession();
        session.invalidate();
        return map;
    }
}
