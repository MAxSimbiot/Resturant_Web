package command;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import entity.Client;
import entity.Product;
import entity.Receipt;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartCommand implements Command{
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
                map.put("receipt",receipt);
            } catch (FailedDAOException e) {
                e.printStackTrace();
            }
        }
        map.put(PageConstants.PAGE,PageConstants.CART_PAGE);
        return map;
    }
}
