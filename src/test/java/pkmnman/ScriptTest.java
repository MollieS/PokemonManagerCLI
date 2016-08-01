package pkmnman;

import org.junit.Test;
import pkmncore.NamedPokemon;
import pkmncore.Pokemon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScriptTest {

    private Script script = new Script();

    @Test
    public void greetsUser() {
        assertTrue(script.greet().contains("Welcome"));
    }

    @Test
    public void instructsAUserToEnterAName() {
        assertTrue(script.promptUser().contains("enter the name of the pokemon you wish to find"));
    }

    @Test
    public void displaysPokemonInformation() {
        Pokemon pokemon = new NamedPokemon("charmander", 6, new String[]{"solar-power", "blaze"});
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
}
