package command.commands;

import constants.PageConstants;
import dao.Impl.ProductDAOImpl;
import dao.Impl.ReceiptDAOImpl;
import dao.ProductDAO;
import entity.Receipt;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DeleteFromCartCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        String pId = request.getParameter("productId");
        int productId = 0;
        if(pId!=null){
            productId = Integer.valueOf(pId);
        }

        int receiptid = 0;
        Object rId = request.getSession().getAttribute("receiptId");
        if(rId!= null){
            receiptid = (Integer) rId;
        }

        if(receiptid!=0){
            boolean success = false;
            ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
            try {
               success = receiptDAO.deleteProductById(receiptid,productId);
            } catch (FailedDAOException e) {
                map.put(PageConstants.PAGE,PageConstants.ERROR_PAGE);
                map.put("errorMsg",e.getMessage());
                e.printStackTrace();
                return map;
            }
            map.put("productDeleted",success);
        }

        map.put(PageConstants.PAGE,PageConstants.COMMAND_CART);
        return map;
    }
}
