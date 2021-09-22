package service;

import command.commands.LoginCommand;
import constants.DAOConstants;
import dao.Impl.ClientDAOImpl;
import entity.Client;
import exception.FailedDAOException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ClientService {
    private static final Logger logger = Logger.getLogger(ClientService.class);

    public static Map<String, Object> validateClientInfo(String login, String password,
                                                  String repPassword, String name, String phone) {
        Map<String, Object> map = new HashMap<>();
        boolean validated = true;

        if (login == null || !DataValidator.validateLogin(login)) {
            map.put("badLogin", true);
            validated = false;
        }
        if (name == null || !DataValidator.validateName(name)) {
            map.put("badName", true);
            validated = false;
        }
        if (password == null || !DataValidator.validatePassword(password)) {
            map.put("badPass", true);
            validated = false;
        }

        if (password != null && repPassword != null && !password.equals(repPassword)) {
            map.put("diffPass", true);
            validated = false;
        }

        if (phone == null || !DataValidator.validatePhone(phone)) {
            map.put("badPhone", true);
            validated = false;
        }

        if (validated) {
            map.put("validated", true);
        } else {
            map.put("registerErrorMsg", true);
        }
        return map;
    }

    public static Client initClient(String login, String password, String name, String phone) {
        Client c = new Client();
        c.setLogin(login);
        c.setPassword(password);
        c.setName(name);
        c.setPhone(phone);
        return c;
    }

    public static Client initClient(int id, String login, String password, String name, String phone) {
        Client c = new Client();
        c.setId(id);
        c.setLogin(login);
        c.setPassword(password);
        c.setName(name);
        c.setPhone(phone);
        return c;
    }
}
