package command.commands;

import constants.PageConstants;
import dao.Impl.ClientDAOImpl;
import dao.Impl.ReceiptDAOImpl;
import entity.Client;
import entity.Receipt;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import repository.ReceiptRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerPageCommand implements Command{
    private static final Logger logger = Logger.getLogger(ManagerPageCommand.class);
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {

        Map<String,Object> map = new HashMap<>();
        ReceiptRepository repository = new ReceiptRepository();
        try {
            List<Receipt> receiptList = repository.getAllReceipts();
            Map<Integer,Client> clients = addClientsByReceipts(receiptList);
            map.put("clients",clients);
            map.put("receiptList",receiptList);
        } catch (FailedDAOException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
        map.put(PageConstants.PAGE,PageConstants.MANAGER_PAGE);
        return map;
    }

    private static Map<Integer,Client> addClientsByReceipts(List<Receipt> receiptList) throws FailedDAOException {
        Map<Integer,Client> clients = new HashMap<>();
        ClientDAOImpl clientDAO = new ClientDAOImpl();
        for(Receipt r:receiptList){
            Client c = clientDAO.getById(r.getClientId());
            clients.put(c.getId(),c);
        }
        return clients;
    }
}
