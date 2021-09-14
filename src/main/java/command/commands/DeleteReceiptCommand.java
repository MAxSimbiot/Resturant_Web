package command.commands;

import constants.PageConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DeleteReceiptCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        map.put(PageConstants.PAGE,PageConstants.COMMAND_CART);
        return map;
    }
}
