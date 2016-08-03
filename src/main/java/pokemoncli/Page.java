package pokemoncli;

import pkmncore.Pokemon;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Message;

public interface Page {

    Action view(Message message);

    Message getMessage();

    Pokemon getPokemon();
}
