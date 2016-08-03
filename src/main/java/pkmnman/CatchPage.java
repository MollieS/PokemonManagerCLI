package pkmnman;

import pkmncore.Pokemon;
import pkmncore.PokemonError;
import pkmncore.storage.PokemonManager;

public class CatchPage implements Page {

    private final Display display;
    private final Input input;
    private final Pokemon pokemon;
    private final PokemonManager pokemonManager;
    private Message message;

    public CatchPage(Display display, Input input, Pokemon pokemon, PokemonManager pokemonManager) {
        this.display = display;
        this.input = input;
        this.pokemon = pokemon;
        this.pokemonManager = pokemonManager;
    }

    public Action view(Message messageToDisplay) {
            message = savePokemon(pokemon);
            return Action.MANAGE;
    }

    private Message savePokemon(Pokemon pokemon) {
        Message message;
        try {
            pokemonManager.catchPokemon(pokemon);
            message = Message.CATCH;
        } catch (PokemonError pokemonError) {
            message = Message.SAVEERROR;
        }
        return message;
    }

    public Message getMessage() {
        return message;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
}
