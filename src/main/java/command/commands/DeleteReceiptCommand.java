package command.commands;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import entity.Receipt;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DeleteReceiptCommand implements Command {
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
            receiptDAO.delete(receipt);
            request.getSession().setAttribute("receiptId",null);
        } catch (FailedDAOException e) {
            map.put(PageConstants.PAGE,PageConstants.ERROR_PAGE);
            map.put("errorMsg",e.getMessage());
            e.printStackTrace();
            return map;
        }

        map.put(PageConstants.PAGE,PageConstants.CART);
        return map;
    }
}
