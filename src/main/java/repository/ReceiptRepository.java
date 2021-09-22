package repository;

import dao.Impl.ReceiptDAOImpl;
import entity.Receipt;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ReceiptRepository {
    private static final Logger logger = LogManager.getLogger(ReceiptRepository.class);

    public Receipt getReceiptByAccountId(int accountId) {
        Receipt receipt = null;
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        try {
            receipt = receiptDAO.getReceiptByAccountId(accountId);
        } catch (FailedDAOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return receipt;
    }
    public List getAllReceipts(){
        List<Receipt> receipts = null;
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        try {
            receipts = receiptDAO.getAll();
        } catch (FailedDAOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return receipts;
    }
    public List<Receipt> getAllClientReceiptsByClientId(int clientId){
        List<Receipt> receipts = null;
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        try {
            receipts = receiptDAO.getAllClientReceiptsById(clientId);
        } catch (FailedDAOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return receipts;
    }

    public boolean deleteReceiptById(int id){
        boolean success = false;
        ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
        try {
            success = receiptDAO.delete(id);
        } catch (FailedDAOException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean saveReceipt(Receipt receipt){
        boolean success = false;
        if(receipt!=null) {
            ReceiptDAOImpl receiptDAO = new ReceiptDAOImpl();
            try {
                success = receiptDAO.create(receipt);
            } catch (FailedDAOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

}
