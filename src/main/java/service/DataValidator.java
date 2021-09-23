package service;

import java.util.regex.Pattern;

public class DataValidator {


    private static final String REGEX_CHECK_LOGIN = "^[a-z0-9_-]{4,15}$";
    private static final String REGEX_CHECK_NAME = "^([a-zA-Zа-яА-Я]{2,15})$";
    private static final String REGEX_CHECK_PASSWORD = "^([a-zA-Z0-9]{6,20}[a-zA-Z0-9])$";
    private static final String REGEX_CHECK_PHONE = "^([+380][0-9]{12})$";

    private static final Pattern CHECK_LOGIN = Pattern.compile(REGEX_CHECK_LOGIN);
    private static final Pattern CHECK_NAME = Pattern.compile(REGEX_CHECK_NAME);
    private static final Pattern CHECK_PASSWORD = Pattern.compile(REGEX_CHECK_PASSWORD);
    private static final Pattern CHECK_PHONE = Pattern.compile(REGEX_CHECK_PHONE);

    private DataValidator(){}

    public static boolean validateLogin(String login){ return CHECK_LOGIN.matcher(login).matches();}

    public static boolean validateName(String name){ return CHECK_NAME.matcher(name).matches();}

    public static boolean validatePassword(String password){ return CHECK_PASSWORD.matcher(password).matches();}

    public static boolean validatePhone(String phone){ return CHECK_PHONE.matcher(phone).matches();}
}
