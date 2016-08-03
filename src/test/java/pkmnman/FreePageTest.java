package pkmnman;

import org.junit.Before;
import org.junit.Test;
import pkmncore.Pokemon;
import pkmncore.PokemonError;
import pkmncore.storage.PokemonManager;
import pkmncore.testfakes.StorageFake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FreePageTest {

    private InputFake inputFake;
    private StorageFake storageFake;
    private FreePage page;
    private DisplayFake displayFake;

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
        storageFake.save("pikachu", "4", new String[]{"lightning-rod", "static"});
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
        storageFake.save("pikachu", "4", new String[]{"lightning-rod", "static"});
        inputFake.set("pikachu", "no");

        page.view(Message.NONE);
        String output = displayFake.read();

        assertTrue(output.contains("PIKACHU"));
    }

    @Test
    public void canChooseNotToFreeAPokemon() throws PokemonError {
        storageFake.save("pikachu", "4", new String[]{"lightning-rod", "static"});
        inputFake.set("pikachu", "no");

        Action action = page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.NONE, message);
        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void loopsForValidInput() throws PokemonError {
        storageFake.save("pikachu", "4", new String[]{"lightning-rod", "static"});
        inputFake.set("pikachu", "invalid");

        Action action = page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.INPUTERROR, message);
        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void returnsThePokemonFreed() throws PokemonError {
        storageFake.save("pikachu", "4", new String[]{"lightning-rod", "static"});

        inputFake.set("pikachu", "yes");
        page.view(Message.FREE);
        Pokemon pokemon = page.getPokemon();

        assertEquals("pikachu", pokemon.getName());
    }
}
