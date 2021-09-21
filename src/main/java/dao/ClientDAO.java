package dao;

import entity.Client;
import exception.FailedDAOException;

public interface ClientDAO<K> extends AbstractDAO<K,Client>{
         Client getClientByLoginAndPassword(String login,String password) throws FailedDAOException;
}
