package pokemoncli.pages;

import org.junit.Before;
import org.junit.Test;
import pokemoncli.ui.DisplayFake;
import pokemoncli.ui.InputFake;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemonmanager.Pokemon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenuPageTest {

    private InputFake input;
    private DisplayFake display;
    private MenuPage menuPage;

    @Before
    public void setUp() {
        display = new DisplayFake(new Script());
        input = new InputFake();
        menuPage = new MenuPage(display, input);
    }

    @Test
    public void showsMenu() {
        input.set("QUIT");

        menuPage.view(Message.NONE);
        String output = display.read();

        assertTrue(output.contains("WELCOME"));
        assertTrue(output.contains("SEARCH"));
        assertTrue(output.contains("MANAGE"));
        assertTrue(output.contains("QUIT"));
    }

    @Test
    public void returnsEnumValueOfQuit() {
        input.set("QUIT");

        Action action = menuPage.view(Message.NONE);

        assertEquals(Action.QUIT, action);
    }

    @Test
    public void returnsAInvalidInputErrorIfInvalidInput() {
        input.set("not valid", "quit");

        menuPage.view(Message.NONE);
        Message message = menuPage.getMessage();

        assertEquals(Message.INPUTERROR, message);
    }

    @Test
    public void displaysMessageIfInvalidInput() {
        input.set("QUIT");

        menuPage.view(Message.INPUTERROR);
        String output = display.read();

        assertTrue(output.contains("didn't seem"));
    }

    @Test
    public void displaysMessageIfPokemonNotFound() {
        input.set("QUIT");

        menuPage.view(Message.NOTFOUND);
        String output = display.read();

        assertTrue(output.contains("This pokemon does not exist"));
    }

    @Test
    public void returnsANullPokemon() {
        input.set("QUIT");

        menuPage.view(Message.NONE);
        Pokemon pokemon = menuPage.getPokemon();

        assertEquals(Pokemon.NULL, pokemon);
    }

}
