package service;

import dao.Impl.ReceiptDAOImpl;
import entity.Receipt;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repository.ReceiptRepository;

public class ReceiptService {
    private static final Logger logger = LogManager.getLogger(ReceiptService.class);
    //Method checks if receipt is not null and has status under 7, meaning it is still in the process and should be displayed
    //as active receipt in cart page.
    //If receipt is null or finished, method returns new receipt.
    public static Receipt checkReceipt(Receipt receipt, int clientId)  {
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        if(receipt==null||receipt.getStatusId() >= 7){
            try {
                receipt = new Receipt();
                receipt.setClientId(clientId);
                receipt.setStatusId(1);
                receiptDAO.create(receipt);
                receipt = receiptDAO.getReceiptByAccountId(clientId);
            }catch (FailedDAOException e){
                logger.log(Level.ERROR,e.getMessage());
            }
        }
        return receipt;
    }

    public boolean updateReceiptStatusById(int statusId,int receiptId){
        boolean success = false;
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        try {
           success = receiptDAO.updateStatusById(statusId,receiptId);
        } catch (FailedDAOException e) {
            e.printStackTrace();
        }
        return success;
    }
    public boolean deleteProductFromReceiptById(int receiptId, int productId){
        boolean success = false;
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        try {
            success = receiptDAO.deleteProductById(receiptId,productId);
        } catch (FailedDAOException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean insertProductIntoReceipt(int receiptId, int productId, int quantity){
        boolean success = false;
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        try {
            success = receiptDAO.insertProductIntoReceipt(receiptId,productId,quantity);
        } catch (FailedDAOException e) {
            e.printStackTrace();
        }
        return success;
    }
}
