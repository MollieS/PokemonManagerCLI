package pkmnman;

import org.junit.Test;
import pkmncore.*;

import static org.junit.Assert.assertTrue;

public class ApplicationRunnerTest {

    @Test
    public void runsTheApplication() {
        SearchEngine search = new PokemonSearch("http://pokeapi.co/api/v2/pokemon/");
        PokemonFinder finder = new PokemonFinder(search);
        StorageUnit dbManager = new DBManager("jdbc:postgresql://127.0.0.1:5432/pokemon_test", "test", "test", "org.postgresql.Driver");
        PokemonManager manager = new PokemonManager(dbManager);
        InputFake input = new InputFake();
        input.set("SEARCH", "pikachu", "no", "QUIT");
        DisplayFake display = new DisplayFake(new Script());
        ApplicationRunner applicationRunner = new ApplicationRunner(finder, manager, input, display);

        applicationRunner.start();
        String output = display.read();
        assertTrue(output.contains("WELCOME"));
        assertTrue(output.contains("PIKACHU"));
    }

    @Test
    public void canSaveAPokemon() {
        SearchEngine search = new PokemonSearch("http://pokeapi.co/api/v2/pokemon/");
        StorageUnit dbManager = new DBManager("jdbc:postgresql://127.0.0.1:5432/pokemon_test", "test", "test", "org.postgresql.Driver");
        PokemonManager manager = new PokemonManager(dbManager);
        PokemonFinder finder = new PokemonFinder(search);
        InputFake input = new InputFake();
        input.set("ADD", "pikachu", "yes", "VIEW", "QUIT");
        DisplayFake display = new DisplayFake(new Script());
        ApplicationRunner applicationRunner = new ApplicationRunner(finder, manager, input, display);

        applicationRunner.start();
        String output = display.read();
        assertTrue(output.contains("ADD a pokemon"));
        assertTrue(output.contains("you have caught 1 pokemon"));
    }

}
