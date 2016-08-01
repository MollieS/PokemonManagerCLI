package pkmnman;

import org.junit.Test;
import pkmncore.PokemonFinder;
import pkmncore.PokemonSearch;
import pkmncore.SearchEngine;

import static org.junit.Assert.assertTrue;

public class ApplicationRunnerTest {

    @Test
    public void runsTheApplication() {
        SearchEngine search = new PokemonSearch("http://pokeapi.co/api/v2/pokemon/");
        PokemonFinder finder = new PokemonFinder(search);
        InputFake input = new InputFake();
        input.set("pikachu");
        DisplayFake display = new DisplayFake(new Script());
        ApplicationRunner applicationRunner = new ApplicationRunner(finder, input, display);

        applicationRunner.start();
        String output = display.read();
        assertTrue(output.contains("Welcome"));
        assertTrue(output.contains("PIKACHU"));
    }

}
