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
        menuPage(Action.NONE);
    }

    private void menuPage(Action action) {
        display.clearScreen();
        display.greet();
        if (action == Action.INPUTERROR) {
            display.invalidInput();
        }
        getUserAction();
    }

    private void searchPage() {
        display.clearScreen();
        display.showSearchHeader();
        display.promptUser();
        Pokemon pokemon = findPokemon();
        searchedPokemonPage(pokemon);
    }

    private void searchedPokemonPage(Pokemon pokemon) {
        display.showDetails(pokemon);
        display.checkIfCaught(pokemon.getName());
        Action action = confirmSave(pokemon);
        managePage(action, pokemon.getName());
    }

    private Pokemon findPokemon() {
        String name = formatInput(input.get());
        Pokemon pokemon = finder.find(name);
        display.clearScreen();
        return pokemon;
    }

    private void managePage(Action action, String pokemonName) {
        display.clearScreen();
        display.showViewHeader();
        displayCaughtPokemon();
        displayActionRequired(action, pokemonName);
        display.managementMenu();
        getManageAction();
    }

    private void getManageAction() {
        String answer = loopForValidInput();
        if ("add".equals(answer)) {
            addPage();
        } else if ("free".equals(answer)) {
            freePage();
        } else if ("back".equals(answer)) {
            menuPage(Action.NONE);
        } else if ("quit".equals(answer)) {
            display.goodbye();
        }
    }

    private String loopForValidInput() {
        String answer = formatInput(input.get());
        while(!validAnswer(answer)) {
            display.invalidInput();
            answer = formatInput(input.get());
        }
        return answer;
    }

    private boolean validAnswer(String answer) {
        return answer.equals("add") || answer.equals("quit") || answer.equals("free") || answer.equals("back");
    }

    private void addPage() {
        display.clearScreen();
        display.showAddHeader();
        display.promptForPokemon();
        String name = formatInput(input.get());
        Pokemon pokemon = finder.find(name);
        pokemonPage(pokemon);
    }

    private void pokemonPage(Pokemon pokemon) {
        display.clearScreen();
        display.showDetails(pokemon);
        Action action = Action.NONE;
        if (!pokemon.equals(Pokemon.NULL)) {
            display.askForSave();
            action = confirmSave(pokemon);
        }
        managePage(action, pokemon.getName());
    }

    private Action confirmSave(Pokemon pokemon) {
        String confirmation = formatInput(input.get());
        Action action = Action.NONE;
        if ("yes".equals(confirmation)) {
            action = savePokemon(pokemon);
        } else if (!"no".equals(confirmation)){
            display.invalidInput();
            confirmSave(pokemon);
        }
        return action;
    }

    private void freePage() {
        display.clearScreen();
        display.showFreeHeader();
        List<Pokemon> caughtPokemon = manager.viewCaughtPokemon();
        caughtPokemonDisplay(caughtPokemon);
        display.promptForNameToFree();
        String name = formatInput(input.get());
        Pokemon pokemon = caughtPokemon(name);
        if (pokemon != Pokemon.NULL) {
            pokemonFreePage(pokemon);
        } else {
            managePage(Action.FREEERROR, name);
        }
    }

    private Pokemon caughtPokemon(String name) {
        List<Pokemon> caughtPokemon = manager.viewCaughtPokemon();
        for (Pokemon pokemon : caughtPokemon) {
            if (name.equals(pokemon.getName())) {
                return pokemon;
            }
        }
        return Pokemon.NULL;
    }

    private void pokemonFreePage(Pokemon pokemon) {
        display.clearScreen();
        display.showDetails(pokemon);
        display.confirmFreedom(pokemon.getName());
        String answer = formatInput(input.get());
        setPokemonFree(pokemon.getName(), answer);
    }

    private void setPokemonFree(String name, String answer) {
        Action action = Action.NONE;
        if (("yes").equals(answer)) {
            try {
                manager.setFree(name);
                action = Action.FREE;
            } catch (PokemonError pokemonError) {
                action = Action.FREEERROR;
            }
        }
        managePage(action, name);
    }

    private void getUserAction() {
        display.showMenu();
        String action = formatInput(input.get());
        if ("search".equals(action)) {
            searchPage();
        } else if ("manage".equals(action)) {
            managePage(Action.NONE, "");
        } else if ("quit".equals(action)) {
            display.goodbye();
        } else {
            menuPage(Action.INPUTERROR);
        }
    }

    private String formatInput(String input) {
        return input.toLowerCase().trim();
    }

    private Action savePokemon(Pokemon pokemon) {
        Action action;
        try {
            manager.catchPokemon(pokemon);
            action = Action.CATCH;
        } catch (PokemonError pokemonError) {
            action = Action.SAVEERROR;
        }
        return action;
    }

    private void displayCaughtPokemon() {
        List<Pokemon> caughtPokemon = manager.viewCaughtPokemon();
        if (caughtPokemon.isEmpty()) {
            display.noPokemon();
        } else {
            caughtPokemonDisplay(caughtPokemon);
        }
    }

    private void caughtPokemonDisplay(List<Pokemon> caughtPokemon) {
        display.showPokemonCount(caughtPokemon.size());
        showAllPokemon(caughtPokemon);
    }

    private void showAllPokemon(List<Pokemon> caughtPokemon) {
        caughtPokemon.forEach(display::showDetails);
    }

    private void displayActionRequired(Action action, String pokemonName) {
        if (action == Action.CATCH) {
            display.confirmSave(pokemonName);
        } else if (action == Action.FREE) {
            display.confirmPokemonIsFree(pokemonName);
        } else if (action == Action.INPUTERROR) {
            display.invalidInput();
        } else if (action == Action.SAVEERROR) {
            display.saveError(pokemonName);
        } else if (action == Action.FREEERROR) {
            display.freeError(pokemonName);
        }
    }

    private enum Action {
        CATCH,
        FREE,
        INPUTERROR,
        NONE,
        SAVEERROR,
        FREEERROR
    }
}
