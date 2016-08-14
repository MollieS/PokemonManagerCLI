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

public class FreePage implements Page {

    private final Display display;
    private final Input input;
    private final PokemonManager pokemonManager;
    private Message message;
    private Pokemon pokemon;

    public FreePage(Display display, Input input, PokemonManager pokemonManager) {
        this.display = display;
        this.input = input;
        this.pokemonManager = pokemonManager;
    }

    public Action view(Message toBeDisplayed) {
        display.promptUser(Action.FREE);
        String name = input.get().trim().toLowerCase();
        List<Pokemon> caughtPokemon = getCaughtPokemon();
        pokemon = findPokemon(name, caughtPokemon);
        tryToFreePokemon(name);
        return Action.MANAGE;
    }

    private void tryToFreePokemon(String name) {
        if (pokemon != Pokemon.NULL) {
            freePokemon(name);
        } else {
            message = Message.FREEERROR;
        }
    }

    private void freePokemon(String name) {
        confirmFree();
        String answer = input.get().trim().toLowerCase();
        processInput(name, answer);
    }

    private List<Pokemon> getCaughtPokemon() {
        List<Pokemon> caughtPokemon = new ArrayList<>();
        try {
            caughtPokemon = pokemonManager.viewCaughtPokemon();
        } catch (PokemonError pokemonError) {
            pokemonError.printStackTrace();
        }
        return caughtPokemon;
    }

    private void processInput(String name, String answer) {
        switch (answer) {
            case "yes":
                setPokemonFree(name);
                break;
            case "no":
                message = Message.NONE;
                break;
            default:
                message = Message.INPUTERROR;
                break;
        }
    }

    private void setPokemonFree(String name) {
        try {
            pokemonManager.setFree(name);
            message = Message.FREE;
        } catch (PokemonError pokemonError) {
            message = Message.FREEERROR;
        }
    }

    private void confirmFree() {
        display.clearScreen();
        display.showDetails(pokemon);
        display.checkDecision(pokemon.getName(), Action.FREE);
    }

    private Pokemon findPokemon(String name, List<Pokemon> caughtPokemon) {
        for (Pokemon pokemon : caughtPokemon) {
            if (pokemon.getName().equals(name)) {
                return pokemon;
            }
        }
        return Pokemon.NULL;
    }

    public Message getMessage() {
        return message;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
}
