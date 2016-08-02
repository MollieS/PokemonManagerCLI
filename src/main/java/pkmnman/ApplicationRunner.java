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
        displayActionRequired(action, "");
        redirectUser();
    }

    private void redirectUser() {
        display.showMenu();
        String action = formatInput(input.get());
        redirect(action);
    }

    private void redirect(String action) {
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

    private void searchPage() {
        display.clearScreen();
        display.showSearchHeader();
        display.promptUser();
        pokemonPage(findPokemon());
    }

    private void pokemonPage(Pokemon pokemon) {
        display.clearScreen();
        display.showDetails(pokemon);
        if (pokemon == Pokemon.NULL) {
            redirectUser();
        } else {
            catchPokemon(pokemon);
        }
    }

    private void managePage(Action action, String pokemonName) {
        display.clearScreen();
        display.showViewHeader();
        displayCaughtPokemon();
        displayActionRequired(action, pokemonName);
        display.managementMenu();
        getManageAction();
    }

    private void catchPokemon(Pokemon pokemon) {
        display.checkIfCaught(pokemon.getName());
        Action action = confirmSave(pokemon);
        managePage(action, pokemon.getName());
    }

    private Pokemon findPokemon() {
        return finder.find(formatInput(input.get()));
    }

    private void getManageAction() {
        String answer = loopForValidInput();
        if ("add".equals(answer)) {
            searchPage();
        } else if ("free".equals(answer)) {
            freePage();
        } else if ("back".equals(answer)) {
            menuPage(Action.NONE);
        } else if ("quit".equals(answer)) {
            display.goodbye();
        }
    }

    private Action confirmSave(Pokemon pokemon) {
        return getAction(pokemon, formatInput(input.get()));
    }

    private Action getAction(Pokemon pokemon, String confirmation) {
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
        caughtPokemon.forEach(display::showDetails);
    }

    private void displayActionRequired(Action action, String pokemonName) {
        switch (action) {
            case CATCH:
                display.confirmSave(pokemonName);
                break;
            case FREE:
                display.confirmPokemonIsFree(pokemonName);
                break;
            case INPUTERROR:
                display.invalidInput();
                break;
            case SAVEERROR:
                display.saveError(pokemonName);
                break;
            case FREEERROR:
                display.freeError(pokemonName);
                break;
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

    private String formatInput(String input) {
        return input.toLowerCase().trim();
    }

    private boolean validAnswer(String answer) {
        return answer.equals("add") || answer.equals("quit") || answer.equals("free") || answer.equals("back");
    }

    private enum Action {
        CATCH,
        FREE,
        INPUTERROR,
        SAVEERROR,
        FREEERROR,
        NONE
    }
}
