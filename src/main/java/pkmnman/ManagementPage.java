package pkmnman;

import pkmncore.Pokemon;
import pkmncore.storage.PokemonManager;

import java.util.List;

public class ManagementPage implements Page {

    private final Display display;
    private final Input input;
    private final PokemonManager manager;
    private Pokemon pokemon;
    private Message message;

    public ManagementPage(Display display, Input input, PokemonManager manager, Pokemon pokemon) {
        this.display = display;
        this.input = input;
        this.manager = manager;
        this.pokemon = pokemon;
    }

    public Action view(Message toDisplay) {
        display.clearScreen();
        display.showViewHeader();
        List<Pokemon> caughtPokemon = manager.viewCaughtPokemon();
        showPokemon(caughtPokemon);
        display.showPokemonCount(caughtPokemon.size());
        showMessage(toDisplay);
        pokemon = Pokemon.NULL;
        display.managementMenu();
        String answer = input.get().toUpperCase().trim();
        Action action = getAction(answer);
        if (action != Action.NONE) return action;
        message = Message.INPUTERROR;
        return Action.MANAGE;
    }

    private Action getAction(String answer) {
        for (Action action : Action.values()) {
            if (action.toString().equals(answer)) {
                return action;
            }
        }
        return Action.NONE;
    }

    private void showMessage(Message toDisplay) {
        if (toDisplay == Message.CATCH) {
            display.confirmSave(pokemon.getName());
        } else if (toDisplay == Message.FREE) {
            display.confirmPokemonIsFree(pokemon.getName());
        } else if (toDisplay == Message.SAVEERROR) {
            display.saveError(pokemon.getName());
        } else if (toDisplay == Message.FREEERROR) {
            display.freeError(pokemon.getName());
        } else if (toDisplay == Message.INPUTERROR) {
            display.invalidInput();
        }
    }

    private void showPokemon(List<Pokemon> caughtPokemon) {
        if (caughtPokemon.isEmpty()) {
            display.noPokemon();
        } else {
            for (Pokemon pokemon : caughtPokemon) {
                display.showDetails(pokemon);
            }
        }
    }

    public Message getMessage() {
        return message;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
}
