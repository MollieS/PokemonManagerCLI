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

    public void searchPage() {
        display.clearScreen();
        display.showSearchHeader();
        display.promptUser();
        Pokemon pokemon = findPokemon();
        searchedPokemonPage(pokemon);
    }

    private Pokemon findPokemon() {
        String name = input.get();
        Pokemon pokemon = finder.find(name);
        display.clearScreen();
        return pokemon;
    }

    public void addPage() {
        display.clearScreen();
        display.showAddHeader();
        display.promptForPokemon();
        String name = input.get();
        Pokemon pokemon = finder.find(name);
        pokemonPage(pokemon);
    }

    private void viewPage() {
        display.clearScreen();
        display.showViewHeader();
        List<Pokemon> caughtPokemon = manager.viewCaughtPokemon();
        displayCaughtPokemon(caughtPokemon);
    }

    private void getUserAction() {
        display.showMenu();
        String action = input.get().toUpperCase().trim();
        switch (action) {
            case "SEARCH":
                searchPage();
                break;
            case "ADD":
                addPage();
                break;
            case "VIEW":
                viewPage();
                break;
            case "QUIT":
                display.goodbye();
                break;
            default:
                display.clearScreen();
                display.invalidInput();
                getUserAction();
                break;
        }
    }

    private void searchedPokemonPage(Pokemon pokemon) {
        display.showDetails(pokemon);
        display.checkIfCaught(pokemon.getName());
        confirmSave(pokemon);
    }

    private void pokemonPage(Pokemon pokemon) {
        display.clearScreen();
        display.showDetails(pokemon);
        display.askForSave();
        confirmSave(pokemon);
    }

    private void savePokemon(Pokemon pokemon) {
        try {
            manager.catchPokemon(pokemon);
            display.confirmSave(pokemon.getName());
        } catch (PokemonError pokemonError) {
            display.invalidPokemon(pokemon.getName() + " has already been caught!");
        }
    }

    private void displayCaughtPokemon(List<Pokemon> caughtPokemon) {
        if (caughtPokemon.isEmpty()) {
            noCaughtPokemon();
        } else {
            caughtPokemonDisplay(caughtPokemon);
        }
    }

    private void caughtPokemonDisplay(List<Pokemon> caughtPokemon) {
        display.showPokemonCount(caughtPokemon.size());
        showAllPokemon(caughtPokemon);
        getUserAction();
    }

    private void showAllPokemon(List<Pokemon> caughtPokemon) {
        for (Pokemon pokemon : caughtPokemon) {
            display.showDetails(pokemon);
        }
    }

    private void noCaughtPokemon() {
        display.noPokemon();
        display.askForCatch();
        askToCatch();
    }

    private void askToCatch() {
        String answer = input.get();
        if ("yes".equals(answer)) {
            addPage();
        } else if ("no".equals(answer)){
            getUserAction();
        } else {
            display.invalidInput();
            askToCatch();
        }
    }

    private void confirmSave(Pokemon pokemon) {
        String confirmation = input.get();
        switch (confirmation) {
            case "yes":
                savePokemon(pokemon);
                getUserAction();
                break;
            case "no":
                getUserAction();
                break;
            default:
                display.invalidInput();
                confirmSave(pokemon);
                break;
        }
    }
}
