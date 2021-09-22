package command.commands;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.ReceiptService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ChangeReceiptStatusCommand implements Command {
    private static final Logger logger = Logger.getLogger(ChangeReceiptStatusCommand.class);

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        int statusId = Integer.parseInt(request.getParameter("statusId"));
        int receiptId = Integer.parseInt(request.getParameter("receiptId"));
        ReceiptService receiptService = new ReceiptService();
        boolean success = receiptService.updateReceiptStatusById(statusId, receiptId);

        map.put(PageConstants.PAGE, PageConstants.COMMAND_MANAGER_PAGE);
        return map;
    }
}
