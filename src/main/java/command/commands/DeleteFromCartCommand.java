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

public class DeleteFromCartCommand implements Command {
    private static final Logger logger = Logger.getLogger(DeleteFromCartCommand.class);
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        String pId = request.getParameter("productId");
        int productId = 0;
        if(pId!=null){
            productId = Integer.parseInt(pId);
        }

        int receiptid = 0;
        String rId = request.getParameter("receiptId");
        if(rId!= null){
            receiptid = Integer.parseInt(rId);
        }

        if(receiptid!=0){
            boolean success = false;
            ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
            try {
               success = receiptDAO.deleteProductById(receiptid,productId);
            } catch (FailedDAOException e) {
                map.put(PageConstants.PAGE,PageConstants.ERROR_PAGE);
                map.put("errorMsg",e.getMessage());
                logger.log(Level.ERROR,e.getMessage());
                return map;
            }
            map.put("productDeleted",success);
        }

        map.put(PageConstants.PAGE,PageConstants.CART);
        return map;
    }
}
