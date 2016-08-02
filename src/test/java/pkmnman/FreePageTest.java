package pkmnman;

import org.junit.Test;
import pkmncore.Pokemon;
import pkmncore.PokemonError;
import pkmncore.storage.PokemonManager;
import pkmncore.testfakes.StorageFake;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FreePageTest {

    @Test
    public void getsNameOfPokemonToFree() throws PokemonError {
        InputFake inputFake = new InputFake();
        DisplayFake displayFake = new DisplayFake(new Script());
        StorageFake storageFake = new StorageFake();
        storageFake.save("pikachu", "4", new String[]{"lightning-rod", "static"});
        PokemonManager pokemonManager = new PokemonManager(storageFake);
        FreePage page = new FreePage(displayFake, inputFake, pokemonManager);

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
        InputFake inputFake = new InputFake();
        DisplayFake displayFake = new DisplayFake(new Script());
        StorageFake storageFake = new StorageFake();
        PokemonManager pokemonManager = new PokemonManager(storageFake);
        FreePage page = new FreePage(displayFake, inputFake, pokemonManager);

        inputFake.set("pikachu", "yes");
        Action action = page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.FREEERROR, message);
        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void showsDetailsOfPokemon() throws PokemonError {
        InputFake inputFake = new InputFake();
        DisplayFake displayFake = new DisplayFake(new Script());
        StorageFake storageFake = new StorageFake();
        storageFake.save("pikachu", "4", new String[]{"lightning-rod", "static"});
        PokemonManager pokemonManager = new PokemonManager(storageFake);
        FreePage page = new FreePage(displayFake, inputFake, pokemonManager);

        inputFake.set("pikachu", "no");
        page.view(Message.NONE);
        String output = displayFake.read();

        assertTrue(output.contains("PIKACHU"));
    }

    @Test
    public void canChooseNotToFreeAPokemon() throws PokemonError {
        InputFake inputFake = new InputFake();
        DisplayFake displayFake = new DisplayFake(new Script());
        StorageFake storageFake = new StorageFake();
        storageFake.save("pikachu", "4", new String[]{"lightning-rod", "static"});
        PokemonManager pokemonManager = new PokemonManager(storageFake);
        FreePage page = new FreePage(displayFake, inputFake, pokemonManager);

        inputFake.set("pikachu", "no");
        Action action = page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.NONE, message);
        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void loopsForValidInput() throws PokemonError {
        InputFake inputFake = new InputFake();
        DisplayFake displayFake = new DisplayFake(new Script());
        StorageFake storageFake = new StorageFake();
        storageFake.save("pikachu", "4", new String[]{"lightning-rod", "static"});
        PokemonManager pokemonManager = new PokemonManager(storageFake);
        FreePage page = new FreePage(displayFake, inputFake, pokemonManager);

        inputFake.set("pikachu", "invalid");
        Action action = page.view(Message.NONE);
        Message message = page.getMessage();

        assertEquals(Message.INPUTERROR, message);
        assertEquals(Action.MANAGE, action);
    }

    @Test
    public void returnsThePokemonFreed() throws PokemonError {
        InputFake inputFake = new InputFake();
        DisplayFake displayFake = new DisplayFake(new Script());
        StorageFake storageFake = new StorageFake();
        storageFake.save("pikachu", "4", new String[]{"lightning-rod", "static"});
        PokemonManager pokemonManager = new PokemonManager(storageFake);
        FreePage page = new FreePage(displayFake, inputFake, pokemonManager);

        inputFake.set("pikachu", "yes");
        Action action = page.view(Message.NONE);
        Pokemon pokemon = page.getPokemon();

        assertEquals("pikachu", pokemon.getName());
    }
}
