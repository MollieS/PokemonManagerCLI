package pkmnman;

import pkmncore.Pokemon;
import pkmncore.search.PokemonFinder;
import pkmncore.storage.PokemonManager;

public class ApplicationRunner {

    private final PokemonFinder finder;
    private final Input input;
    private final Display display;
    private final PokemonManager manager;
    private final Navigator navigator;

    public ApplicationRunner(PokemonFinder finder, PokemonManager manager, Input input, Display display, Navigator navigator) {
        this.finder = finder;
        this.manager = manager;
        this.input = input;
        this.display = display;
        this.navigator = navigator;
    }

    public void start() {
        display.clearScreen();
        Message message = Message.NONE;
        Pokemon pokemon = Pokemon.NULL;
        Page page = navigator.redirect(Action.MENU, pokemon);
        Action action = page.view(message);
        message = page.getMessage();
        while (action != Action.QUIT) {
            page = navigator.redirect(action, pokemon);
            action = page.view(message);
            pokemon = page.getPokemon();
            message = page.getMessage();
        }
        display.goodbye();
    }
}
