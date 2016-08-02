package pkmnman;

import pkmncore.Pokemon;
import pkmncore.search.PokemonFinder;

public class SearchPage implements Page {

    private Display display;
    private Input input;
    private PokemonFinder finder;
    private Pokemon pokemon;

    public SearchPage(Display display, Input input, PokemonFinder finder) {
        this.display = display;
        this.input = input;
        this.finder = finder;
    }

    public Action view(Message message) {
        display.clearScreen();
        display.showSearchHeader();
        display.promptUser();
        String name = input.get().toLowerCase().trim();
        this.pokemon = finder.find(name);
        return Action.SHOWPOKEMON;
    }

    public Message getMessage() {
        if (pokemon == Pokemon.NULL) { return Message.NOTFOUND; }
        return Message.NONE;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
}
