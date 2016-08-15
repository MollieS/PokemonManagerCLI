package pokemoncli.pages;

import org.junit.Before;
import org.junit.Test;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemonmanager.Pokemon;
import pokemonmanager.pokemon.NamedPokemon;
import pokemonmanager.storage.PokemonManager;
import pokemonmanager.testfakes.StorageFake;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CatchPageTest {

    private CatchPage catchPage;

    @Before
    public void setUp() {
        PokemonManager manager = new PokemonManager(new StorageFake());
        Pokemon pokemon = new NamedPokemon("pikachu", "4", Arrays.asList("lightning-rod", "static"));
        this.catchPage = new CatchPage(pokemon, manager);
    }

    @Test
    public void redirectsToManagePageIfPokemonIsSaved() {
        Action action = catchPage.view(Message.NONE);

        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void returnsCatchMessageIfPokemonIsCaught() {
        catchPage.view(Message.NONE);
        Message message = catchPage.getMessage();

        assertEquals(Message.CATCH, message);
    }

    @Test
    public void returnsThePokemonIfCaught() {
        catchPage.view(Message.NONE);
        Pokemon pokemon = catchPage.getPokemon();

        assertEquals("pikachu", pokemon.getName());
    }
}
