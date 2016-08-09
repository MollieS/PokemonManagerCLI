package pokemoncli.pages;

import org.junit.Before;
import org.junit.Test;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemoncli.ui.DisplayFake;
import pokemoncli.ui.InputFake;
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

        page.view(Message.NONE);
        String output = displayFake.read();

        assertTrue(output.contains("Which pokemon do you want to set free"));
        assertTrue(output.contains("Are you sure you want to set pikachu free?"));
    }

    @Test
    public void redirectsToManagePageIfPokemonIsFreed() throws PokemonError {
        storageFake.save(pokemon);
        inputFake.set("pikachu", "yes");

        Action action = page.view(Message.NONE);

        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void returnsFreedMessageIfPokemonIsFreed() throws PokemonError {
        storageFake.save(pokemon);
        inputFake.set("pikachu", "yes");

        page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.FREE, message);
    }

    @Test
    public void returnsErrorIfNoPokemonAreFreed() {
        inputFake.set("pikachu", "yes");

        page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.FREEERROR, message);
    }

    @Test
    public void redirectsToManageIfPokemonAreNotFreed() {
        inputFake.set("pikachu", "yes");

        Action action = page.view(Message.NONE);

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

        page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.NONE, message);
    }

    @Test
    public void returnsErrorIfInvalidInput() throws PokemonError {
        storageFake.save(pokemon);
        inputFake.set("pikachu", "invalid");

        page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.INPUTERROR, message);
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