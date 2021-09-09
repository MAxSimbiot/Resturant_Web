package dao.Impl;

import dao.ReceiptDAO;
import exception.FailedDAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO {
    private static final Logger logger = LogManager.getLogger(ReceiptDAOImpl.class);
    @Override
    public List getAll() throws FailedDAOException {
        return null;
    }

    @Override
    public boolean update(Object entity) throws FailedDAOException {
        return false;
    }

    @Override
    public boolean delete(Object o) throws FailedDAOException {
        return false;
    }

    @Override
    public boolean create(Object entity) throws FailedDAOException {
        return false;
    }

    @Override
    public Object getByid(Object o) throws FailedDAOException {
        return null;
    }
}
