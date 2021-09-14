package command.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface Command {
    Map<String,Object> execute(HttpServletRequest request, HttpServletResponse response);
}
