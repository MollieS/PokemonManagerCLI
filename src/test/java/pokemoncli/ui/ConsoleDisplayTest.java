package pokemoncli.ui;

import org.junit.Test;
import pokemoncli.consoleUI.ConsoleDisplay;
import pokemoncli.consoleUI.Script;
import pokemoncli.consoleUI.WriterException;
import pokemoncli.navigation.Action;

import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.assertTrue;

public class ConsoleDisplayTest {

    @Test
    public void greetsAUser() {
        Writer writer = new StringWriter();
        ConsoleDisplay consoleDisplay = new ConsoleDisplay(new Script(), writer);

        consoleDisplay.greet();

        String output = writer.toString();
        assertTrue(output.contains("WELCOME TO POKEMON MANAGER"));
    }

    @Test
    public void showMenu() {
        Writer writer = new StringWriter();
        ConsoleDisplay consoleDisplay = new ConsoleDisplay(new Script(), writer);

        consoleDisplay.showMenu();

        String output = writer.toString();
        assertTrue(output.contains("SEARCH for a pokemon"));
        assertTrue(output.contains("MANAGE your pokemon"));
        assertTrue(output.contains("QUIT Pokemon Manager"));
    }

    @Test
    public void promptsUserForInput() {
        Writer writer = new StringWriter();
        ConsoleDisplay consoleDisplay = new ConsoleDisplay(new Script(), writer);

        consoleDisplay.promptUser(Action.SEARCH);

        String output = writer.toString();

        assertTrue(output.contains("SEARCH"));
    }

    @Test(expected = WriterException.class)
    public void issueWithWritingThrowsException() {
        Writer writerThrowingException = new StringWriter() {
            @Override
            public void write(String str) {
                throw new RuntimeException();
            }
        };

        ConsoleDisplay consoleDisplay = new ConsoleDisplay(new Script(), writerThrowingException);
        consoleDisplay.greet();
    }
}


