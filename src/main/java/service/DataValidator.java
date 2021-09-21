package service;

public class DataValidator {
    private DataValidator(){}

    private static final String REGEX_CHECK_LOGIN = "^[a-z0-9_-]{3,15}$";
    private static final String REGEX_CHECK_NAME = "^([a-zA-Zа-яА-Я]{2,15})$";
    private static final String REGEX_CHECK_PASSWORD = "^([\\w]{6,20}[a-zA-Z0-9])$";
    private static final String REGEX_CHECK_PHONE = "^([+380][0-9]{12})$";


    public static boolean validateLogin(String login){ return login.matches(REGEX_CHECK_LOGIN);}

    public static boolean validateName(String name){ return name.matches(REGEX_CHECK_NAME);}

    public static boolean validatePassword(String password){ return password.matches(REGEX_CHECK_PASSWORD);}

    public static boolean validatePhone(String phone){ return phone.matches(REGEX_CHECK_PHONE);}
}
