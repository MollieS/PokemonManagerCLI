package pokemoncli.pages;

import pokemoncli.Display;
import pokemoncli.Input;
import pokemoncli.Page;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemonmanager.Pokemon;
import pokemonmanager.PokemonError;
import pokemonmanager.storage.PokemonManager;

import java.util.ArrayList;
import java.util.List;

public class ManagementPage implements Page {

    private final Display display;
    private final Input input;
    private final PokemonManager manager;
    private Pokemon pokemon;
    private Message message;
    private Action action;

    public ManagementPage(Display display, Input input, PokemonManager manager, Pokemon pokemon, Action action) {
        this.display = display;
        this.input = input;
        this.manager = manager;
        this.pokemon = pokemon;
        this.action = action;
    }

    public Action view(Message toDisplay) {
        showPageContent();
        showMessage(toDisplay);
        pokemon = Pokemon.NULL;
        display.managementMenu();
        String answer = input.get().toUpperCase().trim();
        Action action = getUserAction(answer);
        return getAction(action);
    }

    private Action getAction(Action action) {
        if (action != Action.NONE) return action;
        message = Message.INPUTERROR;
        return Action.MANAGE;
    }

    private void showPageContent() {
        display.clearScreen();
        display.showHeader(action);
        List<Pokemon> caughtPokemon = getAllPokemon();
        showPokemon(caughtPokemon);
        display.showPokemonCount(caughtPokemon.size());
    }

    private List<Pokemon> getAllPokemon() {
        List<Pokemon> caughtPokemon = new ArrayList<>();
        try {
            caughtPokemon = manager.viewCaughtPokemon();
        } catch (PokemonError pokemonError) {
            pokemonError.printStackTrace();
        }
        return caughtPokemon;
    }

    private Action getUserAction(String answer) {
        for (Action action : Action.values()) {
            if (action.toString().equals(answer)) {
                return action;
            }
        }
        return Action.NONE;
    }

    private void showMessage(Message toDisplay) {
        switch (toDisplay) {
            case CATCH:
                display.confirmSave(pokemon.getName());
                break;
            case FREE:
                display.confirmPokemonIsFree(pokemon.getName());
                break;
            case SAVEERROR:
                display.saveError(pokemon.getName());
                break;
            case FREEERROR:
                display.freeError(pokemon.getName());
                break;
            case INPUTERROR:
                display.invalidInput();
                break;
        }
    }

    private void showPokemon(List<Pokemon> caughtPokemon) {
        if (caughtPokemon.isEmpty()) {
            display.noPokemon();
        } else {
            caughtPokemon.forEach(display::showDetails);
        }
    }

    public Message getMessage() {
        return message;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
}
