package pokemoncli.pages;

import org.junit.Before;
import org.junit.Test;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemoncli.ui.DisplayFake;
import pokemoncli.ui.InputFake;
import pokemonmanager.Pokemon;
import pokemonmanager.pokemon.NamedPokemon;
import pokemonmanager.storage.PokemonManager;
import pokemonmanager.testfakes.StorageFake;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManagementPageTest {

    private InputFake input;
    private DisplayFake displayFake;
    private ManagementPage managementPage;

    @Before
    public void setUp() {
        this.displayFake = new DisplayFake(new Script());
        this.input = new InputFake();
        Pokemon pokemon = new NamedPokemon("pikachu", "4", Arrays.asList("lightning-rod", "static"));
        this.managementPage = new ManagementPage(displayFake, input, new PokemonManager(new StorageFake()), pokemon, Action.MANAGE);
    }

    @Test
    public void showsMenuOfOptions() {
        input.set("QUIT");

        managementPage.view(Message.NONE);
        String output = displayFake.read();

        assertTrue(output.contains("ADD"));
        assertTrue(output.contains("FREE"));
        assertTrue(output.contains("QUIT"));
    }

    @Test
    public void showsMessageIfPokemonIsCaught() {
        input.set("QUIT");

        managementPage.view(Message.CATCH);
        String output = displayFake.read();

        assertTrue(output.contains("pikachu was caught!"));
    }

    @Test
    public void showsMessageIfPokemonIsFreed() {
        input.set("QUIT");

        managementPage.view(Message.FREE);
        String output = displayFake.read();

        assertTrue(output.contains("pikachu has been set free"));
    }

    @Test
    public void redirectsToManagePageForInvalidInput() {
        input.set("not valid input");

        Action action = managementPage.view(Message.NONE);

        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void returnsInvalidInputMessageForInvalidInput() {
        input.set("not valid input");

        managementPage.view(Message.NONE);
        Message message = managementPage.getMessage();

        assertEquals(Message.INPUTERROR, message);
    }

    @Test
    public void displaysFreeError() {
        input.set("QUIT");

        managementPage.view(Message.FREEERROR);
        String output = displayFake.read();

        assertTrue(output.contains("pikachu has not been caught!"));
    }

    @Test
    public void displaysInputError() {
        input.set("QUIT");

        managementPage.view(Message.INPUTERROR);
        String output = displayFake.read();

        assertTrue(output.contains("didn't seem to be valid"));
    }

    @Test
    public void redirectsToMenuIfChoiceIsMenu() {
        input.set("MENU");

        Action action = managementPage.view(Message.NONE);

        assertEquals(Action.MENU, action);
    }
}
