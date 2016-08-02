package pkmnman;

import org.junit.Before;
import org.junit.Test;
import pkmncore.SearchEngine;
import pkmncore.StorageUnit;
import pkmncore.search.PokemonFinder;
import pkmncore.storage.PokemonManager;
import pkmncore.testfakes.SearchFake;
import pkmncore.testfakes.StorageFake;

import static org.junit.Assert.assertFalse;
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
        assertTrue(output.contains("SEARCH"));
        assertTrue(output.contains("MANAGE"));
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
    public void canAddAPokemonFromSearchPage() {
        input.set("SEARCH", "pikachu", "yes", "VIEW", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("you have caught 1 pokemon"));
    }

    @Test
    public void canViewAllCaughtPokemon() {
        input.set("MANAGE", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("YOUR POKEMON"));
    }

    @Test
    public void canAddANewPokemon() {
        input.set("MANAGE", "ADD", "pikachu", "yes", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("pikachu was caught!"));
        assertTrue(output.contains("you have caught 1 pokemon"));
    }

    @Test
    public void canChooseNotToAddAPokemon() {
        input.set("MANAGE", "ADD", "pikachu", "no", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertFalse(output.contains("pikachu was caught!"));
    }

    @Test
    public void cannotAddSamePokemonTwice() {
        input.set("MANAGE", "ADD", "pikachu", "yes", "ADD", "pikachu", "yes", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("pikachu has already been caught!"));
    }

    @Test
    public void cannotAddAPokemonThatDoesNotExist() {
        input.set("MANAGE", "ADD", "mollie", "yes", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertFalse(output.contains("caught pokemon collection"));
    }

    @Test
    public void canSetAPokemonFree() {
        input.set("MANAGE", "ADD", "pikachu", "yes", "FREE", "pikachu", "yes", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("pikachu has been set free!"));
        assertTrue(output.contains("You haven't caught any pokemon!"));
    }

    @Test
    public void cannotSetFreeAPokemonThatIsNotCaught() {
        input.set("MANAGE", "FREE", "charmander", "yes", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("That answer didn't seem to be valid"));
    }

    @Test
    public void canGoBackToMainMenuFromManagePage() {
        input.set("MANAGE", "BACK", "SEARCH", "pikachu", "no", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("SEARCH"));
        assertTrue(output.contains("PIKACHU"));
    }

    @Test
    public void loopsForValidMenuOption() {
        input.set("not a valid response", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("That answer didn't seem to be valid"));
    }

    @Test
    public void loopsForValidSaveConfirmation() {
        input.set("MANAGE", "ADD", "pikachu", "aksdhaksjhda", "no", "quit");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("That answer didn't seem to be valid"));
    }

    @Test
    public void loopsForValidFreeConfirmation() {
        input.set("MANAGE", "FREE", "pikachu", "aksdhaksjhda", "no", "quit");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("That answer didn't seem to be valid"));
    }

    @Test
    public void loopsForValidCatchAnswer() {
        input.set("VIEW", "ahsdkaj", "QUIT");
        applicationRunner.start();
        String output = display.read();

        assertTrue(output.contains("That answer didn't seem to be valid"));
    }
}
