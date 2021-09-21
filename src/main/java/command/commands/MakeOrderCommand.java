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

public class MakeOrderCommand implements Command{
    private static final Logger logger = Logger.getLogger(MakeOrderCommand.class);
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        String id = request.getParameter("receiptId");
        int receiptId;
        boolean success = false;
        if(id!=null){
            receiptId = Integer.valueOf(id);
            ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
            try {
                success = receiptDAO.updateStatusById(2,receiptId);
            } catch (FailedDAOException e) {
                map.put(PageConstants.PAGE,PageConstants.ERROR_PAGE);
                map.put("errorMsg",e.getMessage());
                logger.log(Level.ERROR,e.getMessage());
                return map;
            }
            if(success){
                map.put("statusUpdated",true);
            }
        }

        map.put(PageConstants.PAGE,PageConstants.CART);
        return map;
    }
}
