package dao.Impl;

import dao.ClientDAO;
import entity.Client;
import exception.FailedDAOException;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    @Override
    public Client getClientByLoginAndPassword(String login, String password) {
        return null;
    }

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
