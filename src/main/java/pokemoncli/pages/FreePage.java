package pokemoncli.pages;

import pokemoncli.Display;
import pokemoncli.Input;
import pokemoncli.Page;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemonmanager.Pokemon;
import pokemonmanager.PokemonError;
import pokemonmanager.storage.PokemonManager;

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
        display.promptForNameToFree();
        String name = input.get().trim().toLowerCase();
        List<Pokemon> caughtPokemon = null;
        caughtPokemon = getPokemon(caughtPokemon);
        pokemon = findPokemon(name, caughtPokemon);
        if (pokemon != Pokemon.NULL) {
            confirmFree();
            String answer = input.get().trim().toLowerCase();
            processInput(name, answer);
        } else {
            message = Message.FREEERROR;
        }
        return Action.MANAGE;
    }

    private List<Pokemon> getPokemon(List<Pokemon> caughtPokemon) {
        try {
            caughtPokemon = pokemonManager.viewCaughtPokemon();
        } catch (PokemonError pokemonError) {
            pokemonError.printStackTrace();
        }
        return caughtPokemon;
    }

    private void processInput(String name, String answer) {
        if (answer.equals("yes")) {
            setPokemonFree(name);
        } else if (answer.equals("no")) {
            message = Message.NONE;
        } else {
            message = Message.INPUTERROR;
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
        display.confirmFreedom(pokemon.getName());
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
