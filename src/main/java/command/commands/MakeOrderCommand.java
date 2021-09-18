package command.commands;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MakeOrderCommand implements Command{
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
                e.printStackTrace();
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
