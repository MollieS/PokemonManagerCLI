package pokemoncli.ui;

import org.junit.Test;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Action;
import pokemonmanager.Pokemon;
import pokemonmanager.pokemon.NamedPokemon;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScriptTest {

    private Script script = new Script();

    @Test
    public void greetsUser() {
        assertTrue(script.menuHeader().contains("WELCOME TO POKEMON MANAGER"));
    }

    @Test
    public void displaysSearchPage() {
        assertTrue(script.header(Action.SEARCH).contains("SEARCH"));
    }

    @Test
    public void instructsAUserToEnterAName() {
        assertTrue(script.promptUser(Action.SEARCH).contains("enter the name of the pokemon you wish to SEARCH"));
    }

    @Test
    public void displaysPokemonInformation() {
        Pokemon pokemon = new NamedPokemon("charmander", "6", Arrays.asList("solar-power", "blaze"));
        String output = script.showDetails(pokemon);
        assertTrue(output.contains("CHARMANDER"));
        assertTrue(output.contains("6"));
        assertTrue(output.contains("solar-power"));
        assertTrue(output.contains("blaze"));
    }

    @Test
    public void onlyDisplaysMessageIfPokemonDoesNotExist() {
        Pokemon pokemon = Pokemon.NULL;
        String output = script.showDetails(pokemon);
        assertTrue(output.contains("THIS POKEMON DOES NOT EXIST"));
        assertFalse(output.contains("height"));
    }

    @Test
    public void showsAMenuOfActions() {
        assertTrue(script.showMenu().contains("SEARCH"));
        assertTrue(script.showMenu().contains("MANAGE"));
        assertTrue(script.showMenu().contains("QUIT"));
    }

    @Test
    public void asksWhichPokemonToAdd() {
        assertTrue(script.promptUser(Action.CATCH).contains("wish to CATCH"));
    }

    @Test
    public void asksForConfirmationOfCatch() {
        assertTrue(script.askForConfirmation().contains("Is this the pokemon you caught?"));
    }

    @Test
    public void showsHowManyPokemonAreCaught() {
        assertTrue(script.showCount(1).contains("you have caught 1 pokemon"));
    }

    @Test
    public void showsErrorMessage() {
        assertTrue(script.saveError("pikachu").contains("pikachu has already been caught!"));
    }

    @Test
    public void showsAMessageWhenNoPokemonCaught() {
        assertTrue(script.noCaughtPokemon().contains("You haven't caught any pokemon!"));
    }

    @Test
    public void asksToAddACatch() {
        assertTrue(script.askForCatch().contains("add a pokemon"));
    }

    @Test
    public void saysGoodbye() {
        assertTrue(script.goodbye().contains("Goodbye!"));
    }

    @Test
    public void showsInvalidInputMessage() {
        assertTrue(script.invalidInput().contains("valid"));
    }

    @Test
    public void promptsUserForNameOfPokemonToSetFree() {
        assertTrue(script.promptUser(Action.FREE).contains("wish to FREE"));
    }

    @Test
    public void asksForConfirmationForSettingAPokemonFree() {
        assertTrue(script.checkUserAction("pikachu", Action.FREE).contains("pikachu the pokemon you want to FREE?"));
    }

    @Test
    public void confirmsAPokemonHasBeenSetFree() {
        assertTrue(script.displayFreedom("pikachu").contains("pikachu has been set free!"));
    }

    @Test
    public void showsErrorIfPokemonNotCaughtIfFreed() {
        assertTrue(script.freeError("pikachu").contains("pikachu has not been caught!"));
    }
}
