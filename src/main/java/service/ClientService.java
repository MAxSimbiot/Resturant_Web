package service;

import constants.DAOConstants;

import java.util.HashMap;
import java.util.Map;

public class ClientService {

    public Map<String,Object> validateRegistration(String login, String password, String repPassword, String name, String phone ){
        Map<String,Object> map = new HashMap<>();
        if(login!=null
                &&password!=null
                &&repPassword.equals(password)
                &&name!=null
                &&phone!=null&&login.length()>0){
            map.put("validated","All right");
        }else{
            map.put("errorMsg","Incorrect Enter");
        }
        return map;
    }
}
