package pkmnman;


import pkmncore.search.PokemonFinder;
import pkmncore.search.PokemonSearch;
import pkmncore.storage.DBManager;
import pkmncore.storage.PokemonManager;

public class Main {

    public static void main(String[] args) {
        Display display = new ConsoleDisplay(new Script());
        Input input = new ConsoleInput();
        PokemonFinder finder = new PokemonFinder(new PokemonSearch("http://pokeapi.co/api/v2/pokemon/"));
        DBManager dbManager = new DBManager("jdbc:postgresql://127.0.0.1:5432/pokemon", "test", "test", "org.postgresql.Driver");
        PokemonManager pokemonManager = new PokemonManager(dbManager);
        ApplicationRunner runner = new ApplicationRunner(finder, pokemonManager, input, display);
        runner.start();
    }
}
