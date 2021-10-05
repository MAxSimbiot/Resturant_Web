package command.commands;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import entity.Client;
import entity.Receipt;
import exception.FailedDAOException;
import repository.ReceiptRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoToCabinetCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap();
        final Client client = (Client) request.getSession().getAttribute("client");
        int clientId = client.getId();
        ReceiptRepository repository = new ReceiptRepository();
        List<Receipt> clientReceipts = repository.getAllClientReceiptsByClientId(clientId);
        map.put("receiptHistory", clientReceipts);
        map.put(PageConstants.PAGE, PageConstants.CLIENT_PAGE);
        return map;
    }
}
