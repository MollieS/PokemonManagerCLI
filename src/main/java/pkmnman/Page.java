package pkmnman;

import pkmncore.Pokemon;

public interface Page {

    Action view(Message message);

    Message getMessage();

    Pokemon getPokemon();
}
