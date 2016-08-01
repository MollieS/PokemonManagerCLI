package pkmnman;

import pkmncore.PokemonFinder;
import pkmncore.PokemonSearch;

public class Main {

    public static void main(String[] args) {
        Display display = new ConsoleDisplay(new Script());
        Input input = new ConsoleInput();
        PokemonFinder finder = new PokemonFinder(new PokemonSearch("http://pokeapi.co/api/v2/pokemon/"));
        ApplicationRunner runner = new ApplicationRunner(finder, input, display);
        runner.start();
    }
}
