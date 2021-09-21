package command.commands;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import entity.Client;
import entity.Receipt;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoToCabinetCommand implements Command {
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap();
        Client client = (Client) request.getSession().getAttribute("client");
        int clientId = client.getId();
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        try {
            List<Receipt> clientReceipts = receiptDAO.getAllClientReceiptsById(clientId);
            map.put("receiptHistory", clientReceipts);
        } catch (FailedDAOException e) {
            map.put(PageConstants.PAGE, PageConstants.ERROR_PAGE);
            map.put("errorMsg", e.getMessage());
            e.printStackTrace();
            return map;
        }
        map.put(PageConstants.PAGE, PageConstants.CLIENT_PAGE);
        return map;
    }
}
