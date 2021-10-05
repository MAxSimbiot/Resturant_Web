package command.commands;

import constants.DAOConstants;
import constants.PageConstants;
import entity.Client;
import entity.Receipt;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import repository.ReceiptRepository;
import service.ReceiptService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AddToCartCommand implements Command {
    private static final Logger logger = Logger.getLogger(AddToCartCommand.class);

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        Object log = request.getSession().getAttribute("logged");
        boolean logged = false;
        if (log == null) {
            map.put(PageConstants.PAGE, PageConstants.LOGIN_PAGE);
        } else {
            logged = (Boolean) log;
        }
        if (logged) {
            final Client client = (Client) request.getSession().getAttribute(DAOConstants.CLIENT);
            int clientId = client.getId();
            ReceiptRepository repository = new ReceiptRepository();
            Receipt receipt = repository.getReceiptByAccountId(clientId);
            receipt = ReceiptService.checkReceipt(receipt, clientId);
            if (receipt.getStatusId() > 1) {
                map.put(PageConstants.PAGE, PageConstants.CART);
                return map;
            }
            ReceiptService receiptService = new ReceiptService();
            boolean done = receiptService.insertProductIntoReceipt(receipt.getId(),
                    Integer.valueOf(request.getParameter("productId")), 1);
            if (done) {
                map.put("added", "true");
                logger.log(Level.INFO, "Client by id " + clientId + " added " + request.getParameter("productId"));
            } else {
                map.put("added", "false");
            }
            map.put(PageConstants.PAGE, PageConstants.INDEX);
        }
        return map;
    }


}
