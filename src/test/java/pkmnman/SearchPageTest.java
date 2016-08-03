package pkmnman;

import org.junit.Before;
import org.junit.Test;
import pkmncore.Pokemon;
import pkmncore.search.PokemonFinder;
import pkmncore.testfakes.SearchFake;

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
    public void showsPageContent() {
        input.set("pikachu");

        searchPage.view(Message.NONE);
        Message message = searchPage.getMessage();
        String output = display.read();

        assertTrue(output.contains("SEARCH"));
        assertTrue(output.contains("wish to find"));
        assertEquals(Message.NONE, message);
    }

    @Test
    public void findsAPokemon() {
        input.set("pikachu");

        searchPage.view(Message.NONE);
        Message message = searchPage.getMessage();
        Pokemon pokemon = searchPage.getPokemon();

        assertEquals(Message.NONE, message);
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
