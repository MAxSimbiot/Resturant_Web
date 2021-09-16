package command.commands;

import constants.PageConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class goToCabinetCommand implements Command{
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map map = new HashMap();
        map.put(PageConstants.PAGE,PageConstants.CLIENT_PAGE);
        return map;
    }
}
