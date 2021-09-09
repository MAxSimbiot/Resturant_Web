package dao.Impl;

import dao.RoleDAO;
import entity.Role;
import exception.FailedDAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = LogManager.getLogger(RoleDAOImpl.class);
    @Override
    public Role getRoleByName(String name) {
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
