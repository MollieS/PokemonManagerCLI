package pkmnman;

import org.junit.Before;
import org.junit.Test;
import pkmncore.SearchEngine;
import pkmncore.StorageUnit;
import pkmncore.search.PokemonFinder;
import pkmncore.storage.PokemonManager;
import pkmncore.testfakes.SearchFake;
import pkmncore.testfakes.StorageFake;

import static org.junit.Assert.assertTrue;

public class ApplicationRunnerTest {


    private InputFake input;
    private DisplayFake display;
    private ApplicationRunner applicationRunner;

    @Before
    public void setUp() {
        SearchEngine search = new SearchFake();
        PokemonFinder finder = new PokemonFinder(search);
        StorageUnit dbManager = new StorageFake();
        PokemonManager manager = new PokemonManager(dbManager);

        this.display = new DisplayFake(new Script());
        this.input = new InputFake();
        Navigator navigator = new Navigator(display, input, finder, manager);
        this.applicationRunner = new ApplicationRunner(display, navigator);
    }

    @Test
    public void showsMenu() {
        input.set("QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("WELCOME"));
        assertTrue(output.contains("SEARCH"));
        assertTrue(output.contains("MANAGE"));
        assertTrue(output.contains("QUIT"));
    }

    @Test
    public void canAddAPokemonFromMainMenu() {
        input.set("SEARCH", "pikachu", "yes", "QUIT");

        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("PIKACHU"));
        assertTrue(output.contains("pikachu was caught"));
        assertTrue(output.contains("you have caught 1 pokemon"));
    }

    @Test
    public void cannotAddTheSamePokemonTwice() {
        input.set("SEARCH", "pikachu", "yes", "ADD", "pikachu", "yes", "QUIT");

        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("pikachu has already been caught!"));
    }
}
