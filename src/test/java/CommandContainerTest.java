import command.CommandContainer;
import command.commands.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

public class CommandContainerTest {

    List<String> namesOk;
    List<String> namesBad;
    List<Command> commands;

    @Before
    public void setUp() throws Exception {
        namesOk = Arrays.asList("makeOrder",
                              "deleteFromCart",
                              "logIn",
                              "mainPage");
        namesBad = Arrays.asList("goHell",
                                " ",
                                null,
                                "/]\\]//");
        commands = Arrays.asList(new MakeOrderCommand(),
                                new DeleteFromCartCommand(),
                                new LoginCommand(),
                                new MainPageCommand());
    }

    @Test
    public void commandContainterTestGood(){
        for(int i = 0; i < commands.size();i++){
            Assert.assertTrue((CommandContainer.get(namesOk.get(i)).getClass()).
                                             equals(commands.get(i).getClass()));
        }
    }
    @Test
    public void commandContainterTestBad(){
        for(String s:namesBad){
            Assert.assertTrue((CommandContainer.get(s).getClass().equals(ErrorCommand.class)));
        }
    }

    @After
    public void tearDown() throws Exception {
        List<String> namesOk = null;
        List<String> namesBad = null;
        List<Command> commands = null;
    }

}
