package pokemoncli;

import org.junit.Test;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemoncli.pages.ManagementPage;
import pokemonmanager.Pokemon;
import pokemonmanager.pokemon.NamedPokemon;
import pokemonmanager.storage.PokemonManager;
import pokemonmanager.testfakes.StorageFake;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManagementPageTest {

    @Test
    public void showsMenuOfOptions() {
        DisplayFake displayFake = new DisplayFake(new Script());
        InputFake input = new InputFake();
        ManagementPage managementPage = new ManagementPage(displayFake, input, new PokemonManager(new StorageFake()), Pokemon.NULL);

        input.set("QUIT");
        managementPage.view(Message.NONE);
        String output = displayFake.read();

        assertTrue(output.contains("ADD"));
        assertTrue(output.contains("FREE"));
        assertTrue(output.contains("QUIT"));
    }

    @Test
    public void showsMessageIfPokemonIsCaught() {
        DisplayFake displayFake = new DisplayFake(new Script());
        InputFake input = new InputFake();
        Pokemon pokemon = new NamedPokemon("pikachu", "4", Arrays.asList("lightning-rod", "static"));
        ManagementPage managementPage = new ManagementPage(displayFake, input, new PokemonManager(new StorageFake()), pokemon);

        input.set("QUIT");
        managementPage.view(Message.CATCH);
        String output = displayFake.read();

        assertTrue(output.contains("pikachu was caught!"));
    }

    @Test
    public void showsMessageIfPokemonIsFreed() {
        DisplayFake displayFake = new DisplayFake(new Script());
        InputFake input = new InputFake();
        Pokemon pokemon = new NamedPokemon("pikachu", "4", Arrays.asList("lightning-rod", "static"));
        ManagementPage managementPage = new ManagementPage(displayFake, input, new PokemonManager(new StorageFake()), pokemon);

        input.set("QUIT");
        managementPage.view(Message.FREE);
        String output = displayFake.read();

        assertTrue(output.contains("pikachu has been set free"));
    }

    @Test
    public void loopsForValidInput() {
        DisplayFake displayFake = new DisplayFake(new Script());
        InputFake input = new InputFake();
        Pokemon pokemon = new NamedPokemon("pikachu", "4", Arrays.asList("lightning-rod", "static"));
        ManagementPage managementPage = new ManagementPage(displayFake, input, new PokemonManager(new StorageFake()), pokemon);

        input.set("not valid input");
        Action action = managementPage.view(Message.NONE);
        String output = displayFake.read();
        Message message = managementPage.getMessage();

        assertEquals(Action.MANAGE, action);
        assertEquals(Message.INPUTERROR, message);
    }

    @Test
    public void displaysFreeError() {
        DisplayFake displayFake = new DisplayFake(new Script());
        InputFake input = new InputFake();
        Pokemon pokemon = new NamedPokemon("pikachu", "4", Arrays.asList("lightning-rod", "static"));
        ManagementPage managementPage = new ManagementPage(displayFake, input, new PokemonManager(new StorageFake()), pokemon);

        input.set("QUIT");
        managementPage.view(Message.FREEERROR);
        String output = displayFake.read();

        assertTrue(output.contains("pikachu has not been caught!"));
    }

    @Test
    public void displaysInputError() {
        DisplayFake displayFake = new DisplayFake(new Script());
        InputFake input = new InputFake();
        Pokemon pokemon = new NamedPokemon("pikachu", "4", Arrays.asList("lightning-rod", "static"));
        ManagementPage managementPage = new ManagementPage(displayFake, input, new PokemonManager(new StorageFake()), pokemon);

        input.set("QUIT");
        managementPage.view(Message.INPUTERROR);
        String output = displayFake.read();

        assertTrue(output.contains("didn't seem to be valid"));
    }
}
