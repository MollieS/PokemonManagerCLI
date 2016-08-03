package pkmnman;


import pkmncore.StorageUnit;
import pkmncore.search.PokemonFinder;
import pkmncore.search.PokemonSearch;
import pkmncore.storage.DBManager;
import pkmncore.storage.PokemonManager;
import pkmncore.testfakes.SearchFake;
import pkmncore.testfakes.StorageFake;

public class Main {

    public static void main(String[] args) {
        Display display = new ConsoleDisplay(new Script());
        Input input = new ConsoleInput();
        PokemonSearch pokemonSearch = new PokemonSearch("http://pokeapi.co/api/v2/pokemon/");
        SearchFake searchFake = new SearchFake();
        PokemonFinder finder = new PokemonFinder(searchFake);
        StorageUnit fake = new StorageFake();
        DBManager dbManager = new DBManager("jdbc:postgresql://127.0.0.1:5432/pokemon", "molliestephenson", "test", "org.postgresql.Driver");
        PokemonManager pokemonManager = new PokemonManager(dbManager);
        Navigator navigator = new Navigator(display, input, finder, pokemonManager);
        ApplicationRunner runner = new ApplicationRunner(display, navigator);
        runner.start();
    }
}
