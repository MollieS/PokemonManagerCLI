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
        this.applicationRunner = new ApplicationRunner(finder, manager, input, display);
    }

    @Test
    public void showsMenu() {
        input.set("QUIT");

        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("WELCOME"));
        assertTrue(output.contains("ADD"));
        assertTrue(output.contains("SEARCH"));
        assertTrue(output.contains("VIEW"));
        assertTrue(output.contains("QUIT"));
    }


    @Test
    public void searchesForAPokemon() {
        input.set("SEARCH", "pikachu", "no", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("SEARCH"));
        assertTrue(output.contains("find"));
        assertTrue(output.contains("PIKACHU"));
        assertTrue(output.contains("caught pokemon collection?"));
    }

    @Test
    public void asksUserToAddPokemonIfTheyHaventCaughtAny() {
        input.set("VIEW", "no", "quit");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("YOUR POKEMON"));
        assertTrue(output.contains("You haven't caught any pokemon"));
        assertTrue(output.contains("add a pokemon"));
    }

    @Test
    public void canAddAPokemonFromAddPage() {
        input.set("ADD", "pikachu", "yes", "VIEW", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("ADD"));
        assertTrue(output.contains("Which pokemon did you catch?"));
        assertTrue(output.contains("pikachu was caught!"));
        assertTrue(output.contains("you have caught 1 pokemon"));
    }

    @Test
    public void canAddAPokemonFromSearchPage() {
        input.set("SEARCH", "pikachu", "yes", "VIEW", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("you have caught 1 pokemon"));
    }

    @Test
    public void displaysMessageIfPokemonHasBeenCaught() {
        input.set("ADD", "pikachu", "yes", "ADD", "pikachu", "yes", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("pikachu has already been caught!"));
    }

    @Test
    public void canViewCaughtPokemon() {
        input.set("VIEW", "pikachu", "yes", "VIEW", "QUIT");

        applicationRunner.start();
    }

}
