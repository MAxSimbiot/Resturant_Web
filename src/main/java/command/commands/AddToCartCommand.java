package command.commands;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import entity.Client;
import entity.Receipt;
import exception.FailedDAOException;
import service.ReceiptService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AddToCartCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        Object log = request.getSession().getAttribute("logged");
        boolean logged = false;
        if(log==null){
            map.put(PageConstants.PAGE,PageConstants.LOGIN_PAGE);
        }else{
            logged = (Boolean)log ;
        }
        if(logged){
             Client client =(Client) request.getSession().getAttribute("client");
             int clientId = client.getId();
            ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
            try {
                Receipt receipt = receiptDAO.getReceiptByAccountId(clientId);
                if(receipt.getStatusId() > 1){
                    map.put(PageConstants.PAGE,PageConstants.CART);
                    return map;
                }
                receipt = ReceiptService.checkReceipt(receipt,clientId,receiptDAO);
               boolean done =  receiptDAO.insertProductIntoReceipt(
                       receipt.getId(),
                       Integer.valueOf(request.getParameter("productId")),1);
               if(done){
                   map.put("added","true");
               }else{
                   map.put("added","false");
               }
            } catch (FailedDAOException e) {
                map.put(PageConstants.PAGE,PageConstants.ERROR_PAGE);
                map.put("errorMsg",e.getMessage());
                e.printStackTrace();
                return map;
            }
            map.put(PageConstants.PAGE,PageConstants.INDEX);
        }
        return map;
    }


}
