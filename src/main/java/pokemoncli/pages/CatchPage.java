package pokemoncli.pages;

import pokemoncli.Page;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemonmanager.Pokemon;
import pokemonmanager.PokemonError;
import pokemonmanager.storage.PokemonManager;

public class CatchPage implements Page {

    private final Pokemon pokemon;
    private final PokemonManager pokemonManager;
    private Message message;

    public CatchPage(Pokemon pokemon, PokemonManager pokemonManager) {
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
