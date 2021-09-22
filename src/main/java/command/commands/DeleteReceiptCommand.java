package command.commands;

import constants.PageConstants;
import dao.Impl.ReceiptDAOImpl;
import entity.Receipt;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import repository.ReceiptRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DeleteReceiptCommand implements Command {
    private static final Logger logger = Logger.getLogger(DeleteReceiptCommand.class);

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put(PageConstants.PAGE, PageConstants.COMMAND_CART);
        String id = request.getParameter("receiptId");
        int receiptId;
        if (id != null) {
            receiptId = Integer.parseInt(id);
        } else {
            return map;
        }
        ReceiptRepository repository = new ReceiptRepository();
        Receipt receipt = new Receipt();
        receipt.setId(receiptId);
        repository.deleteReceiptById(receiptId);
        request.getSession().setAttribute("receiptId", null);
        map.put(PageConstants.PAGE, PageConstants.CART);
        return map;
    }
}
