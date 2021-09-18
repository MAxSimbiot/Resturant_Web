package service;

import dao.Impl.ReceiptDAOImpl;
import entity.Receipt;
import exception.FailedDAOException;

public class ReceiptService {
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
