package pokemoncli;

import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;
import pokemonmanager.Pokemon;

public interface Page {

    Action view(Message message);

    Message getMessage();

    Pokemon getPokemon();
}
