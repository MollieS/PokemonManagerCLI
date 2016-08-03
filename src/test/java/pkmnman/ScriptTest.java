package pkmnman;

import org.junit.Test;
import pkmncore.Pokemon;
import pkmncore.pokemon.Pokemon.NamedPokemon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScriptTest {

    private Script script = new Script();

    @Test
    public void greetsUser() {
        assertTrue(script.greet().contains("WELCOME"));
    }

    @Test
    public void displaysSearchPage() {
        assertTrue(script.searchPage().contains("SEARCH"));
    }

    @Test
    public void displaysAddPage() {
        assertTrue(script.addingPage().contains("ADD"));
    }

    @Test
    public void displaysCaughtPokemon() {
        assertTrue(script.viewPage().contains("YOUR POKEMON"));
    }

    @Test
    public void instructsAUserToEnterAName() {
        assertTrue(script.promptUser().contains("enter the name of the pokemon you wish to find"));
    }

    @Test
    public void displaysPokemonInformation() {
        Pokemon pokemon = new NamedPokemon("charmander", "6", new String[]{"solar-power", "blaze"});
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
        assertTrue(script.showMenu().contains("ADD"));
        assertTrue(script.showMenu().contains("VIEW"));
        assertTrue(script.showMenu().contains("QUIT"));
    }

    @Test
    public void asksWhichPokemonToAdd() {
        assertTrue(script.promptUserForCaughtPokemon().contains("Which pokemon did you catch?"));
    }

    @Test
    public void asksForConfirmationOfCatch() {
        assertTrue(script.askForConfirmation().contains("Is this the pokemon you caught?"));
    }

    @Test
    public void confirmsPokemonIsSaved() {
        assertTrue(script.confirmSave("pikachu").contains("pikachu was caught!"));
    }

    @Test
    public void showsHowManyPokemonAreCaught() {
      assertTrue(script.showCount(1).contains("you have caught 1 pokemon"));
    }

    @Test
    public void showsErrorMessage() {
        assertTrue(script.saveError("Error!").contains("Something went wrong"));
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

}
