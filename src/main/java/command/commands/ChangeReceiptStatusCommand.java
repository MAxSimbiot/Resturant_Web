package command.commands;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ChangeReceiptStatusCommand implements Command {
    private static final Logger logger = Logger.getLogger(ChangeReceiptStatusCommand.class);
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        int statusId = Integer.parseInt(request.getParameter("statusId"));
        int receiptId = Integer.parseInt(request.getParameter("receiptId"));
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        try {
          boolean success = receiptDAO.updateStatusById(statusId,receiptId);
            System.out.println(success);
        } catch (FailedDAOException e) {
            map.put(PageConstants.PAGE,PageConstants.ERROR_PAGE);
            map.put("errorMsg",e.getMessage());
            logger.log(Level.ERROR,e.getMessage());
            return map;
        }
        map.put(PageConstants.PAGE,PageConstants.COMMAND_MANAGER_PAGE);
        return map;
    }
}
