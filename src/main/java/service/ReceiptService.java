package service;

import dao.Impl.ReceiptDAOImpl;
import entity.Receipt;
import exception.FailedDAOException;

public class ReceiptService {

    //Method checks if receipt is not null and has status under 7, meaning it is still in the process and should be displayed
    //as active receipt in cart page.
    //If receipt is null or finished, method returns new receipt.
    public static Receipt checkReceipt(Receipt receipt, int clientId, ReceiptDAOImpl receiptDAO) throws FailedDAOException {
        if(receipt==null||receipt.getStatusId() >= 7){
            receipt = new Receipt();
            receipt.setClientId(clientId);
            receipt.setStatusId(1);
            receiptDAO.create(receipt);
            receipt = receiptDAO.getReceiptByAccountId(clientId);
        }
        return receipt;
    }
}
