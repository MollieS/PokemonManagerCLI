package pokemoncli;

import pokemoncli.consoleUI.ConsoleDisplay;
import pokemoncli.consoleUI.ConsoleInput;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Navigator;
import pokemonmanager.search.PokemonFinder;
import pokemonmanager.search.PokemonSearch;
import pokemonmanager.storage.DBManager;
import pokemonmanager.storage.PokemonManager;

import java.io.OutputStreamWriter;
import java.io.Writer;

public class Main {

    public static void main(String[] args) {
        Writer writer = new OutputStreamWriter(System.out);
        Display display = new ConsoleDisplay(new Script(), writer);
        Input input = new ConsoleInput();
        PokemonSearch pokemonSearch = new PokemonSearch("http://pokeapi.co/api/v2/pokemon/");
        PokemonFinder finder = new PokemonFinder(pokemonSearch);
        DBManager dbManager = new DBManager("jdbc:postgresql://127.0.0.1:5432/pokemon", "test", "test", "org.postgresql.Driver");
        PokemonManager pokemonManager = new PokemonManager(dbManager);
        Navigator navigator = new Navigator(display, input, finder, pokemonManager);
        ApplicationRunner runner = new ApplicationRunner(display, navigator);
        runner.start();
    }
}
