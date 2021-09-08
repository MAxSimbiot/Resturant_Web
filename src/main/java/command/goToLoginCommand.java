package command;

import constants.PageConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class goToLoginCommand implements Command{
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        map.put("page", PageConstants.LOGIN_PAGE);
        return map;
    }
}
