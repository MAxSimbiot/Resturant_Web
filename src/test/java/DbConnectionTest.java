import dao.DBManager;
import dao.Impl.ReceiptDAOImpl;
import entity.Receipt;
import exception.FailedDAOException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionTest {

 @Test
    public void testReceiptDao(){
     ReceiptDAOImpl receiptDAO= new ReceiptDAOImpl();

     try {
         Receipt receipt = receiptDAO.getReceiptByAccountId(1);
         System.out.println(receipt);
     } catch (FailedDAOException e) {
         e.printStackTrace();
     }

 }

}
