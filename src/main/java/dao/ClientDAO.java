package dao;

import entity.Client;
import exception.FailedDAOException;

public interface ClientDAO extends AbstractDAO{
         Client getClientByLoginAndPassword(String login,String password) throws FailedDAOException;
}
