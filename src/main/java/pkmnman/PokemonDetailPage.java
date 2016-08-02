package pkmnman;

import pkmncore.Pokemon;

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
        Action action;
        if (message == Message.INPUTERROR) {
            display.invalidInput();
        }
        message = Message.NONE;
        display.checkIfCaught(pokemon.getName());
        String answer = input.get().trim().toLowerCase();
        if (answer.equals("yes")) {
            action = Action.CATCH;
        } else if (answer.equals("no")){
            pokemon = Pokemon.NULL;
            action = Action.MENU;
        } else {
            message = Message.INPUTERROR;
            action = Action.SHOWPOKEMON;
        }
        return action;
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
