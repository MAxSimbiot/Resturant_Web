package command.commands;

import constants.PageConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ErrorCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        String errorMessage = "Whoops! This function is still under construction..";
        map.put("errorMessage",errorMessage);
        map.put("page", PageConstants.ERROR_PAGE);
        return map;
    }
}
