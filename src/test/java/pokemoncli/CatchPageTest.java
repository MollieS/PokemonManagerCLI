package pokemoncli;

import org.junit.Before;
import org.junit.Test;
import pkmncore.Pokemon;
import pkmncore.pokemon.NamedPokemon;
import pkmncore.storage.PokemonManager;
import pkmncore.testfakes.StorageFake;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemoncli.pages.CatchPage;

import static org.junit.Assert.assertEquals;

public class CatchPageTest {

    private InputFake inputFake;
    private CatchPage catchPage;

    @Before
    public void setUp() {
        this.inputFake = new InputFake();
        DisplayFake displayFake = new DisplayFake(new Script());
        PokemonManager manager = new PokemonManager(new StorageFake());
        Pokemon pokemon = new NamedPokemon("pikachu", "4", new String[]{"lightning-rod", "static"});
        this.catchPage = new CatchPage(displayFake, inputFake, pokemon, manager);
    }

    @Test
    public void savesAPokemon() {
        inputFake.set("yes");

        Action action = catchPage.view(Message.NONE);
        Message message = catchPage.getMessage();
        Pokemon pokemon = catchPage.getPokemon();

        assertEquals(Action.MANAGE, action);
        assertEquals(Message.CATCH, message);
        assertEquals("pikachu", pokemon.getName());
    }
}
