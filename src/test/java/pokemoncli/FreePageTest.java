package pokemoncli;

import org.junit.Before;
import org.junit.Test;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemoncli.pages.FreePage;
import pokemonmanager.Pokemon;
import pokemonmanager.PokemonError;
import pokemonmanager.pokemon.NamedPokemon;
import pokemonmanager.storage.PokemonManager;
import pokemonmanager.testfakes.StorageFake;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FreePageTest {

    private InputFake inputFake;
    private StorageFake storageFake;
    private FreePage page;
    private DisplayFake displayFake;
    private Pokemon pokemon = new NamedPokemon("pikachu", "4", Arrays.asList("lightning-rod", "static"));

    @Before
    public void setUp() {
        this.inputFake = new InputFake();
        this.displayFake = new DisplayFake(new Script());
        this.storageFake = new StorageFake();
        PokemonManager pokemonManager = new PokemonManager(storageFake);
        this.page = new FreePage(displayFake, inputFake, pokemonManager);

    }

    @Test
    public void getsNameOfPokemonToFree() throws PokemonError {
        storageFake.save(pokemon);
        inputFake.set("pikachu", "yes");

        Action action = page.view(Message.NONE);
        String output = displayFake.read();
        Message message = page.getMessage();

        assertTrue(output.contains("Which pokemon do you want to set free"));
        assertTrue(output.contains("Are you sure you want to set pikachu free?"));
        assertEquals(Message.FREE, message);
        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void cannotFreeIfNoneCaught() {
        inputFake.set("pikachu", "yes");

        Action action = page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.FREEERROR, message);
        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void showsDetailsOfPokemon() throws PokemonError {
        storageFake.save(pokemon);
        inputFake.set("pikachu", "no");

        page.view(Message.NONE);
        String output = displayFake.read();

        assertTrue(output.contains("PIKACHU"));
    }

    @Test
    public void canChooseNotToFreeAPokemon() throws PokemonError {
        storageFake.save(pokemon);
        inputFake.set("pikachu", "no");

        Action action = page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.NONE, message);
        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void loopsForValidInput() throws PokemonError {
        storageFake.save(pokemon);
        inputFake.set("pikachu", "invalid");

        Action action = page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.INPUTERROR, message);
        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void returnsThePokemonFreed() throws PokemonError {
        storageFake.save(pokemon);

        inputFake.set("pikachu", "yes");
        page.view(Message.FREE);
        Pokemon pokemon = page.getPokemon();

        assertEquals("pikachu", pokemon.getName());
    }
}
