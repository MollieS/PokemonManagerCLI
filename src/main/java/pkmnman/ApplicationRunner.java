package pkmnman;

import pkmncore.Pokemon;
import pkmncore.PokemonError;
import pkmncore.search.PokemonFinder;
import pkmncore.storage.PokemonManager;

import java.util.List;

public class ApplicationRunner {

    private final PokemonFinder finder;
    private final Input input;
    private final Display display;
    private final PokemonManager manager;

    public ApplicationRunner(PokemonFinder finder, PokemonManager manager, Input input, Display display) {
        this.finder = finder;
        this.manager = manager;
        this.input = input;
        this.display = display;
    }

    public void start() {
        display.clearScreen();
        display.greet();
        getUserAction();
    }

    private void getUserAction() {
        display.showMenu();
        String action = input.get().toUpperCase().trim();
        if (action.equals("SEARCH")) {
            searchPage();
        } else if (action.equals("ADD")){
            addPage();
        } else if (action.equals("VIEW")) {
            viewPage();
        } else if (action.equals("QUIT")) {
            display.goodbye();
        } else {
            display.clearScreen();
            display.invalidInput();
        }
    }

    public void searchPage() {
        display.clearScreen();
        display.showSearchHeader();
        display.promptUser();
        String name = input.get();
        Pokemon pokemon = finder.find(name);
        display.clearScreen();
        display.showDetails(pokemon);
        display.checkIfCaught(pokemon.getName());
        String answer = input.get();
        if ("yes".equals(answer)) {
            savePokemon(pokemon);
        }
        getUserAction();
    }

    public void addPage() {
        display.clearScreen();
        display.showAddHeader();
        display.promptForPokemon();
        String name = input.get();
        Pokemon pokemon = finder.find(name);
        display.clearScreen();
        display.showDetails(pokemon);
        display.askForSave();
        String confirmation = input.get();
        if (confirmation.equals("yes")) {
            savePokemon(pokemon);
        }
        getUserAction();
    }

    private void savePokemon(Pokemon pokemon) {
        try {
            manager.catchPokemon(pokemon);
            display.confirmSave(pokemon.getName());
        } catch (PokemonError pokemonError) {
            display.invalidPokemon(pokemon.getName() + " has already been caught!");
        }
    }

    public void viewPage() {
        display.clearScreen();
        display.showViewHeader();
        List<Pokemon> caughtPokemon = manager.viewCaughtPokemon();
        if (caughtPokemon.isEmpty()) {
            noCaughtPokemon();
        } else {
            display.showPokemonCount(caughtPokemon.size());
            showAllPokemon(caughtPokemon);
            getUserAction();
        }
    }

    private void showAllPokemon(List<Pokemon> caughtPokemon) {
        for (Pokemon pokemon : caughtPokemon) {
            display.showDetails(pokemon);
        }
    }

    private void noCaughtPokemon() {
        display.noPokemon();
        display.askForCatch();
        String answer = input.get();
        if ("yes".equals(answer)) {
            addPage();
        }
        getUserAction();
    }
}
