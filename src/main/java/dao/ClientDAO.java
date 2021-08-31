package dao;

import entity.Client;

public interface ClientDAO extends AbstractDAO{
         Client getClientByLoginAndPassword(String login,String password);
}
