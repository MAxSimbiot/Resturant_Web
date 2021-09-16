package command.commands;

import constants.DAOConstants;
import constants.PageConstants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LogOutCommand implements Command{

    private static final Logger logger = Logger.getLogger(LogOutCommand.class);
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        Map<String,Object> map  = new HashMap<>();
        map.put(PageConstants.PAGE,PageConstants.COMMAND_MAIN_PAGE);
        return map;
    }
}
