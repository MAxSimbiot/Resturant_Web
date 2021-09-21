package command.commands;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import entity.Receipt;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DeleteReceiptCommand implements Command {
    private static final Logger logger = Logger.getLogger(DeleteReceiptCommand.class);
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        map.put(PageConstants.PAGE,PageConstants.COMMAND_CART);
        String id = request.getParameter("receiptId");
        int receiptId;
        if(id!=null){
            receiptId = Integer.parseInt(id);
        }else{
            return map;
        }
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        Receipt receipt = new Receipt();
        receipt.setId(receiptId);
        try {
            receiptDAO.delete(receipt.getId());
            request.getSession().setAttribute("receiptId",null);
        } catch (FailedDAOException e) {
            map.put(PageConstants.PAGE,PageConstants.ERROR_PAGE);
            map.put("errorMsg",e.getMessage());
            logger.log(Level.ERROR,e.getMessage());
            return map;
        }

        map.put(PageConstants.PAGE,PageConstants.CART);
        return map;
    }
}
