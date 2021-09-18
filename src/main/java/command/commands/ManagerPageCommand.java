package command.commands;

import constants.PageConstants;
import dao.Impl.ClientDAOImpl;
import dao.Impl.ReceiptDAOImpl;
import entity.Client;
import entity.Receipt;
import exception.FailedDAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerPageCommand implements Command{
    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        try {
            List<Receipt> receiptList = receiptDAO.getAll();
            Map<Integer,Client> clients = addClientsByReceipts(receiptList);
            map.put("clients",clients);
            map.put("receiptList",receiptList);
        } catch (FailedDAOException e) {
            e.printStackTrace();
        }
        map.put(PageConstants.PAGE,PageConstants.MANAGER_PAGE);
        return map;
    }

    private static Map<Integer,Client> addClientsByReceipts(List<Receipt> receiptList) throws FailedDAOException {
        Map<Integer,Client> clients = new HashMap<>();
        ClientDAOImpl clientDAO = new ClientDAOImpl();
        for(Receipt r:receiptList){
            Client c = clientDAO.getByid(r.getClientId());
            clients.put(c.getId(),c);
        }
        return clients;
    }
}
