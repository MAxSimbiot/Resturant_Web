package repository;

import dao.Impl.ClientDAOImpl;
import entity.Client;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ClientRepository {
    private static final Logger logger = LogManager.getLogger(ClientRepository.class);

    private ClientDAOImpl clientDAO;

    public Client getClientByLoginPassword(String login, String password) {
        Client client = null;
        clientDAO = new ClientDAOImpl();
        if (login != null && password != null) {
            try {
                client = clientDAO.getClientByLoginAndPassword(login, password);
            } catch (FailedDAOException e) {
                logger.log(Level.ERROR, e.getMessage());
            } catch (Exception ex) {
                logger.log(Level.ERROR,ex.getMessage());
            }
        }
        return client;
    }

    public Client getClientById(int id) {
        Client client = null;
        clientDAO = new ClientDAOImpl();
        try {
            client = clientDAO.getById(id);
        } catch (FailedDAOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return client;
    }

    public List<Client> getAllClients() {
        List<Client> clients = null;
        clientDAO = new ClientDAOImpl();
        try {
            clients = clientDAO.getAll();
        } catch (FailedDAOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return clients;
    }

    public boolean updateClient(Client client){
        boolean success = false;
        if(client!=null){
            clientDAO = new ClientDAOImpl();
            try {
               success = clientDAO.update(client);
            } catch (FailedDAOException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }
        return success;
    }

    public boolean saveClient(Client client){
        boolean success = false;
        if(client!=null){
            clientDAO = new ClientDAOImpl();
            try {
                success = clientDAO.create(client);
            } catch (FailedDAOException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }
        return success;
    }


}
