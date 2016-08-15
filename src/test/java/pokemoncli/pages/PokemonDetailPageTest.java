package pokemoncli.pages;

import org.junit.Before;
import org.junit.Test;
import pokemoncli.ui.DisplayFake;
import pokemoncli.ui.InputFake;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemonmanager.Pokemon;
import pokemonmanager.pokemon.NamedPokemon;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PokemonDetailPageTest {

    private DisplayFake display;
    private InputFake input;
    private PokemonDetailPage pokemonDetailPage;

    @Before
    public void setUp() {
        this.display = new DisplayFake(new Script());
        Pokemon pokemon = new NamedPokemon("pikachu", "4", Arrays.asList("lightning-rod", "static"));
        this.input = new InputFake();
        this.pokemonDetailPage = new PokemonDetailPage(display, input, pokemon);
    }

    @Test
    public void showsPokemonDetails() {
        input.set("no");

        pokemonDetailPage.view(Message.NONE);
        String output = display.read();

        assertTrue(output.contains("PIKACHU"));
    }

    @Test
    public void redirectsToMenuIfNoSave() {
        input.set("no");

        Action action = pokemonDetailPage.view(Message.NONE);

        assertEquals(Action.MENU, action);
    }

    @Test
    public void doesNotReturnAMessageIfNoSave() {
        input.set("no");

        pokemonDetailPage.view(Message.NONE);
        Message message = pokemonDetailPage.getMessage();

        assertEquals(Message.NONE, message);
    }

    @Test
    public void redirectsToCatchPageIfPokemonIsSaved() {
        input.set("yes");

        Action action = pokemonDetailPage.view(Message.NONE);

        assertEquals(Action.CATCH, action);
    }

    @Test
    public void returnsPokemonIfSaved() {
        input.set("yes");

        pokemonDetailPage.view(Message.NONE);
        Pokemon pokemon = pokemonDetailPage.getPokemon();

        assertEquals("pikachu", pokemon.getName());
    }

    @Test
    public void returnsNullPokemonIfNotSaved() {
        input.set("no");

        pokemonDetailPage.view(Message.NONE);
        Pokemon pokemon = pokemonDetailPage.getPokemon();

        assertEquals(Pokemon.NULL, pokemon);
    }

    @Test
    public void returnsAnInputErrorMessageIfInputIsInvalid() {
        input.set("not valid input");

        pokemonDetailPage.view(Message.NONE);
        Message message = pokemonDetailPage.getMessage();

        assertEquals(Message.INPUTERROR, message);
    }

    @Test
    public void redirectsToMenuIfPokemonIsNotFound() {
        PokemonDetailPage pokemonDetailPage = new PokemonDetailPage(display, input, Pokemon.NULL);

        Action action = pokemonDetailPage.view(Message.NONE);

        assertEquals(Action.MENU, action);
    }

    @Test
    public void returnsNotFoundMessageIfPokemonIsNotFound() {
        PokemonDetailPage pokemonDetailPage = new PokemonDetailPage(display, input, Pokemon.NULL);

        pokemonDetailPage.view(Message.NONE);
        Message message = pokemonDetailPage.getMessage();

        assertEquals(Message.NOTFOUND, message);
    }
}