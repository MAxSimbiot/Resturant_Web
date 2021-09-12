package command;

import constants.PageConstants;
import dao.Impl.ProductDAOImpl;
import dao.Impl.ReceiptDAOImpl;
import entity.Client;
import entity.Receipt;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AddToCartCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        boolean logged = (Boolean) request.getSession().getAttribute("logged");
        if(logged){
             Client client =(Client) request.getSession().getAttribute("client");
             int clientId = client.getId();
            ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
            try {
                Receipt receipt = receiptDAO.getReceiptByAccountId(clientId);
               boolean done =  receiptDAO.insertProductIntoReceipt(
                       receipt.getId(),
                       Integer.valueOf(request.getParameter("productId")),1);
               map.put("added","true");
            } catch (FailedDAOException e) {
                map.put("added",false);
                e.printStackTrace();
            }
        }// else --> Implement logic for adding to cart while not logged, or ask to log in!
        map.put(PageConstants.PAGE,PageConstants.COMMAND_MAIN_PAGE);
        return map;
    }
}
