package pokemoncli.pages;

import org.junit.Before;
import org.junit.Test;
import pokemoncli.ui.DisplayFake;
import pokemoncli.ui.InputFake;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Message;
import pokemonmanager.Pokemon;
import pokemonmanager.search.PokemonFinder;
import pokemonmanager.testfakes.SearchFake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchPageTest {

    private InputFake input;
    private DisplayFake display;
    private SearchPage searchPage;

    @Before
    public void setUp() {
        this.display = new DisplayFake(new Script());
        this.input = new InputFake();
        PokemonFinder finder = new PokemonFinder(new SearchFake());
        this.searchPage = new SearchPage(display, input, finder);
    }

    @Test
    public void promptsForANameToSearchFor() {
        input.set("pikachu");

        searchPage.view(Message.NONE);
        String output = display.read();

        assertTrue(output.contains("SEARCH"));
        assertTrue(output.contains("wish to SEARCH"));
    }

    @Test
    public void returnsANullMessageForValidInput() {
        input.set("pikachu");

        searchPage.view(Message.NONE);
        Message message = searchPage.getMessage();

        assertEquals(Message.NONE, message);
    }

    @Test
    public void findsAPokemon() {
        input.set("pikachu");

        searchPage.view(Message.NONE);
        Pokemon pokemon = searchPage.getPokemon();

        assertEquals("pikachu", pokemon.getName());
    }

    @Test
    public void returnsPokemonNullIfPokemonNotFound() {
        input.set("mollie");

        searchPage.view(Message.NONE);
        Pokemon pokemon = searchPage.getPokemon();

        assertEquals(Pokemon.NULL, pokemon);
    }

    @Test
    public void returnsANotFoundMessageIfPokemonNotFound() {
        input.set("mollie");

        searchPage.view(Message.NONE);
        Message message = searchPage.getMessage();

        assertEquals(Message.NOTFOUND, message);
    }
}
