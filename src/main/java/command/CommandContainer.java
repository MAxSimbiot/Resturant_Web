package command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static Map<String,Command> commands = new TreeMap<String,Command>();

    static {
        commands.put("register",new RegisterCommand());
        commands.put("error",new ErrorCommand());
        commands.put("mainPage",new MainPageCommand());
    }

    public static Command get(String commandName){
        if(commandName==null || !commands.containsKey(commandName)){
            return commands.get("error");
        }
        return commands.get(commandName);
    }

}
