package dao.Impl;

import dao.ClientDAO;
import constants.DAOConstants;
import dao.DBManager;
import entity.Client;
import entity.Role;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import service.PasswordEncryprionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO<Integer> {

    private static final Logger logger = Logger.getLogger(ClientDAOImpl.class);

    private static final String FIND_CLIENT_BY_LOGIN_PASSWORD = "SELECT * FROM client " +
            "WHERE login = ? " +
            "AND password = ?;";
    private static final String FIND_CLIENT_BY_LOGIN = "SELECT * FROM client " +
            "WHERE login = ?;";
    private static final String FIND_ALL_CLIENTS = "SELECT * FROM client;";

    private static final String INSERT_NEW_CLIENT = "INSERT INTO client(login,password,name,phone,role_id) " +
            "VALUES (?,?,?,?,?);";

    private static final String UPDATE_ACCOUNT = "UPDATE client SET " +
            "login = ?, " +
            "password = ?, " +
            "name = ?, " +
            "phone = ? " +
            "WHERE id = ?;";
    private static final String FIND_CLIENT_BY_ID = "SELECT * FROM client WHERE id = ?;";

    @Override
    public Client getClientByLoginAndPassword(String login, String password) throws FailedDAOException {
        Connection connection = null;
        Client client = null;
        try {
            connection = DBManager.getInstance().getConnection();
            client = executeGet(connection,login,password,client);
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Can`t get client by login password!", ex);
            throw new FailedDAOException("Can`t get client by login password!");
        } finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return client;
    }

    @Override
    public List<Client> getAll() throws FailedDAOException {
        Connection connection = null;
        List<Client> clients = new ArrayList<>();
        try {
            connection = DBManager.getInstance().getConnection();
            executeGetAll(connection,clients);
        } catch (SQLException ex) {
            DBManager.getInstance().closeConnection(connection);
            logger.log(Level.ERROR, "Can`t get all clients", ex);
            throw new FailedDAOException("Can`t get all clients");
        } finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return clients;
    }

    @Override
    public boolean update(Client client) throws FailedDAOException {
        if (client == null) {
            return false;
        }
        Connection connection = null;
        int rowsUpdated = 0;
        try {
             connection = DBManager.getInstance().getConnection();
             rowsUpdated = executeUpdate(connection,client);
        } catch (Exception ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.log(Level.ERROR, "Can`t update client", ex);
            throw new FailedDAOException("Can`t update client");
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return rowsUpdated == 1;
    }

    @Override
    public boolean create(Client client) throws FailedDAOException {
        if (client == null) {
            return false;
        }
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            executeCreate(connection,client);
            connection.commit();
        } catch (Exception ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            logger.log(Level.ERROR, "Can`t add a client", ex);
            throw new FailedDAOException("Can`t add a client");
        } finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return true;
    }

    @Override
    public Client getById(Integer id) throws FailedDAOException {
        Connection connection = null;
        Client client = null;
        try {
            connection = DBManager.getInstance().getConnection();
            client = executeGetById(connection,id,client);
        } catch (SQLException ex) {
            logger.log(Level.ERROR, "Can`t get client by id!", ex);
            throw new FailedDAOException("Can`t get client by id!");
        } finally {
            DBManager.getInstance().closeConnection(connection);
        }
        return client;
    }

    private Client executeGetById(Connection connection,int id,Client client) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(FIND_CLIENT_BY_ID);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            client = initClient(resultSet);
        }
        ps.close();
        return client;
    }

    @Override
    public boolean delete(Integer id) throws FailedDAOException {
        throw new UnsupportedOperationException("Deletion of account not supported yet");
    }

    private Client executeGet(Connection connection,String login,String password,Client client) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(FIND_CLIENT_BY_LOGIN);
        ps.setString(1, login);
        System.out.println(password);
        ResultSet resultSet = ps.executeQuery();
        try {
            if (resultSet.next()&&
               (PasswordEncryprionService.check(password,resultSet.getString(DAOConstants.PASSWORD)))) {
                client = initClient(resultSet);
            }
        }catch (Exception e){
            logger.log(Level.ERROR,e.getMessage());
            client = null;
        }
        ps.close();
        return client;
    }

    private void executeGetAll(Connection connection,List<Client> clients) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(FIND_ALL_CLIENTS);
        while (set.next()) {
            clients.add(initClient(set));
        }
        statement.close();
    }

    private int executeUpdate(Connection connection,Client client) throws Exception {
        int rowsUpdated;
        PreparedStatement updateStatement = connection.prepareStatement(UPDATE_ACCOUNT);
        initUpdateStatement(client, updateStatement);
        rowsUpdated = updateStatement.executeUpdate();
        updateStatement.close();
        return rowsUpdated;
    }

    private void executeCreate(Connection connection, Client client) throws Exception {
        PreparedStatement ps = connection.prepareStatement(INSERT_NEW_CLIENT);
        initCreateStatement(client, ps);
        ps.executeUpdate();
        ps.close();
    }

    private void initCreateStatement(Client c, PreparedStatement ps) throws Exception {
        int k = 1;
        ps.setString(k++, c.getLogin());
        ps.setString(k++, PasswordEncryprionService.getSaltedHash(c.getPassword()));
        ps.setString(k++, c.getName());
        ps.setString(k++, c.getPhone());
        ps.setInt(k, 2);
    }

    private void initUpdateStatement(Client c, PreparedStatement ps) throws Exception {
        int k = 1;
        ps.setString(k++, c.getLogin());
        ps.setString(k++, PasswordEncryprionService.getSaltedHash(c.getPassword()));
        ps.setString(k++, c.getName());
        ps.setString(k++, c.getPhone());
        ps.setInt(k, c.getId());
    }

    private Client initClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt(DAOConstants.ID));
        client.setLogin(rs.getString(DAOConstants.LOGIN));
        client.setPassword(rs.getString(DAOConstants.PASSWORD));
        client.setName(rs.getString(DAOConstants.NAME));
        client.setPhone(rs.getString(DAOConstants.PHONE));
        client.setRegDate(rs.getDate(DAOConstants.REG_DATE).toLocalDate());
        client.setTotalSpent(rs.getInt(DAOConstants.TOTAL_SPENT));

        int roleId = rs.getInt(DAOConstants.ROLE_ID);
        client.setRoleId(roleId);
        client.setRoleEntity(Role.getByid(roleId));
        return client;
    }
}
