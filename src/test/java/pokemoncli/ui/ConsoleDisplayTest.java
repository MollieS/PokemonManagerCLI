package pokemoncli.ui;

import org.junit.Test;
import pokemoncli.consoleUI.ConsoleDisplay;
import pokemoncli.consoleUI.Script;
import pokemoncli.consoleUI.WriterException;
import pokemoncli.navigation.Action;
import pokemonmanager.Pokemon;
import pokemonmanager.pokemon.NamedPokemon;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class ConsoleDisplayTest {

    private Writer writer = new StringWriter();
    private ConsoleDisplay consoleDisplay = new ConsoleDisplay(new Script(), writer);

    @Test
    public void greetsAUser() {
        consoleDisplay.greet();

        assertTrue(output().contains("WELCOME TO POKEMON MANAGER"));
    }

    @Test
    public void showMenu() {
        consoleDisplay.showMenu();

        assertTrue(output().contains("SEARCH for a pokemon"));
        assertTrue(output().contains("MANAGE your pokemon"));
        assertTrue(output().contains("QUIT Pokemon Manager"));
    }

    @Test
    public void promptsUserForInput() {
        consoleDisplay.promptUser(Action.SEARCH);

        assertTrue(output().contains("SEARCH"));
    }

    @Test
    public void displaysPokemonDetails() {
        Pokemon pokemon = new NamedPokemon("pikachu", "4", Arrays.asList("lightning-rod", "static"));

        consoleDisplay.showDetails(pokemon);

        assertTrue(output().contains("PIKACHU"));
    }

    @Test
    public void confirmsFreedomOfAPokemon() {
        consoleDisplay.checkDecision("pikachu", Action.FREE);

        assertTrue(output().contains("Is pikachu the pokemon you want to FREE?"));
    }

    @Test
    public void saysGoodbye() {
        consoleDisplay.goodbye();

        assertTrue(output().contains("Goodbye"));
    }

    @Test
    public void showsManagementMenu() {
        consoleDisplay.managementMenu();

        assertTrue(output().contains("ADD"));
        assertTrue(output().contains("FREE"));
        assertTrue(output().contains("QUIT"));
        assertTrue(output().contains("MENU"));
    }

    @Test
    public void showsHowManyPokemonHaveBeenCaught() {
        consoleDisplay.showPokemonCount(4);

        assertTrue(output().contains("you have caught 4 pokemon"));
    }

    @Test
    public void confirmsSave() {
        consoleDisplay.confirmSave("pikachu");

        assertTrue(output().contains("pikachu was caught!"));
    }

    @Test
    public void confirmsFreedom() {
        consoleDisplay.confirmPokemonIsFree("pikachu");

        assertTrue(output().contains("pikachu has been set free!"));
    }

    @Test
    public void displaysMessageIfErrorInSave() {
        consoleDisplay.saveError("pikachu");

        assertTrue(output().contains("pikachu has already been caught!"));
    }

    @Test
    public void displaysAMessageIfNoPokemon() {
        consoleDisplay.noPokemon();

        assertTrue(output().contains("haven't caught any pokemon"));
    }

    @Test
    public void showsPageHeader() {
        consoleDisplay.showHeader(Action.SEARCH);

        assertTrue(output().contains("POKEMON SEARCH"));
    }

    @Test
    public void showsMessageForInvalidInput() {
        consoleDisplay.invalidInput();

        assertTrue(output().contains("seem to be valid"));
    }

    @Test
    public void showsMessageIFreeError() {
        consoleDisplay.freeError("pikachu");

        assertTrue(output().contains("pikachu has not been caught!"));
    }

    @Test
    public void showsAMessageIfPokemonIsNotFound() {
        consoleDisplay.pokemonNotFound();

        assertTrue(output().contains("does not exist"));
    }

    @Test
    public void clearsTheScreen() {
        consoleDisplay.clearScreen();

        assertTrue(output().contains("[2J"));
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

    private String output() {
        return writer.toString();
    }
}
