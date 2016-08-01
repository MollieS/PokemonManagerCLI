package pkmnman;

import pkmncore.Pokemon;
import pkmncore.PokemonFinder;

public class ApplicationRunner {

    private final PokemonFinder finder;
    private final Input input;
    private final Display display;

    public ApplicationRunner(PokemonFinder finder, Input input, Display display) {
        this.finder = finder;
        this.input = input;
        this.display = display;
    }

    public void start() {
        display.clearScreen();
        display.greet();
        display.promptUser();
        String name = input.get();
        Pokemon pokemon = finder.find(name);
        display.clearScreen();
        display.showDetails(pokemon);
    }
}
