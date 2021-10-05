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

public class MakeOrderCommand implements Command {
    private static final Logger logger = Logger.getLogger(MakeOrderCommand.class);

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        final String id = request.getParameter("receiptId");
        int receiptId;
        boolean success = false;
        if (id != null) {
            receiptId = Integer.valueOf(id);
            ReceiptService receiptService = new ReceiptService();
            success = receiptService.updateReceiptStatusById(2, receiptId);
            if (success) {
                map.put("statusUpdated", true);
            }
        }

        map.put(PageConstants.PAGE, PageConstants.CART);
        return map;
    }
}
