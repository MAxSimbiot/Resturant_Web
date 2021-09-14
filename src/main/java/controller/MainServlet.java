package controller;

import command.commands.Command;
import command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/MainServlet")
public class MainServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(MainServlet.class);

    private final String PAGE = "page";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           prcess(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prcess(req,resp);
    }

    private void prcess(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        Command command = CommandContainer.get(commandName);
        Map<String,Object>  map = command.execute(request,response);
        for(Map.Entry<String,Object> entry: map.entrySet()){
            request.setAttribute(entry.getKey(),entry.getValue());
        }
        request.getRequestDispatcher((String) map.get(PAGE)).forward(request,response);
    }
}
