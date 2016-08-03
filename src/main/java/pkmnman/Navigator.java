package pkmnman;

import pkmncore.Pokemon;
import pkmncore.search.PokemonFinder;
import pkmncore.storage.PokemonManager;

public class Navigator {

    private Display display;
    private Input input;
    private PokemonFinder pokemonFinder;
    private PokemonManager pokemonManager;

    public Navigator(Display display, Input input, PokemonFinder pokemonFinder, PokemonManager pokemonManager) {
        this.display = display;
        this.input = input;
        this.pokemonFinder = pokemonFinder;
        this.pokemonManager = pokemonManager;

    }

    public Page redirect(Action action, Pokemon pokemon) {
        if (action.equals(Action.SEARCH) || action.equals(Action.ADD)) {
            return new SearchPage(display, input, pokemonFinder);
        } else if (action == Action.MANAGE) {
            return new ManagementPage(display, input, pokemonManager, pokemon);
        } else if (action == Action.SHOWPOKEMON) {
            return new PokemonDetailPage(display, input, pokemon);
        } else if (action == Action.CATCH) {
            return new CatchPage(display, input, pokemon, pokemonManager);
        } else if (action == Action.FREE) {
            return new FreePage(display, input, pokemonManager);
        } else {
            return new MenuPage(display, input);
        }
    }
}
