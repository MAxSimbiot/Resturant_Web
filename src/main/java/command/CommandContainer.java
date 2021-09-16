package command;

import command.commands.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static Map<String, Command> commands = new TreeMap<String,Command>();

    static {
        commands.put("register",new RegisterCommand());
        commands.put("error",new ErrorCommand());
        commands.put("mainPage",new MainPageCommand());
        commands.put("goToLogin",new goToLoginCommand());
        commands.put("goToCabinet",new goToCabinetCommand());
        commands.put("logIn",new LoginCommand());
        commands.put("logOut",new LogOutCommand());
        commands.put("changeLanguage",new ChangeLanguageCommand());
        commands.put("addToCart",new AddToCartCommand());
        commands.put("showCart",new CartCommand());
        commands.put("deleteFromCart",new DeleteFromCartCommand());
        commands.put("deleteReceipt",new DeleteReceiptCommand());

    }

    public static Command get(String commandName){
        if(commandName==null || !commands.containsKey(commandName)){
            return commands.get("error");
        }
        return commands.get(commandName);
    }

}
