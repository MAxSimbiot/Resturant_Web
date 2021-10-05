package command.commands;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import entity.Client;
import entity.Receipt;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import repository.ReceiptRepository;
import service.ReceiptService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CartCommand implements Command {
    private static final Logger logger = Logger.getLogger(CartCommand.class);

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        Object o = request.getSession().getAttribute("logged");
        boolean logged = false;
        if (o != null) {
            logged = (Boolean) o;
        }
        if (logged) {
            final  Client client = (Client) request.getSession().getAttribute("client");
            int clientId = client.getId();
            ReceiptRepository repository = new ReceiptRepository();
            Receipt receipt = repository.getReceiptByAccountId(clientId);
            receipt = ReceiptService.checkReceipt(receipt, clientId);
            map.put("receipt", receipt);
            map.put(PageConstants.PAGE, PageConstants.CART_PAGE);
        } else {
            map.put(PageConstants.PAGE, PageConstants.LOGIN_PAGE);
        }
        return map;
    }
}
