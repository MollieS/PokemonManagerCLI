package pokemoncli;

import pkmncore.Pokemon;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemoncli.navigation.Navigator;

public class ApplicationRunner {

    private final Display display;
    private final Navigator navigator;

    public ApplicationRunner(Display display, Navigator navigator) {
        this.navigator = navigator;
        this.display = display;
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
