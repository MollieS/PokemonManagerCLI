package pokemoncli.pages;

import pkmncore.Pokemon;
import pokemoncli.*;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;

public class PokemonDetailPage implements Page {

    private final Display display;
    private final Input input;
    private Message message;
    private Pokemon pokemon;

    public PokemonDetailPage(Display display, Input input, Pokemon pokemon) {
        this.display = display;
        this.pokemon = pokemon;
        this.input = input;
    }

    public Action view(Message toBeDisplayed) {
        display.clearScreen();
        display.showDetails(pokemon);
        return getAction();
    }

    private Action getAction() {
        if (pokemon == Pokemon.NULL) {
            return pokemonNotFound();
        } else {
            return pokemonFound();
        }
    }

    private Action pokemonFound() {
        message = Message.NONE;
        display.checkIfCaught(pokemon.getName());
        String answer = input.get().trim().toLowerCase();
        return getAction(answer);
    }

    private Action getAction(String answer) {
        Action action;
        if (answer.equals("yes")) {
            action = Action.CATCH;
        } else if (answer.equals("no")){
            action = noSave();
        } else {
            action = invalidInput();
        }
        return action;
    }

    private Action noSave() {
        pokemon = Pokemon.NULL;
        return Action.MENU;
    }

    private Action invalidInput() {
        message = Message.INPUTERROR;
        return Action.SHOWPOKEMON;
    }

    private Action pokemonNotFound() {
        message = Message.NOTFOUND;
        pokemon = Pokemon.NULL;
        return Action.MENU;
    }

    public Message getMessage() {
        return message;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
}