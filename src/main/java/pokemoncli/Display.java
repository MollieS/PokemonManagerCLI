package pokemoncli;

import pokemoncli.navigation.Action;
import pokemonmanager.Pokemon;

public interface Display {

    void greet();

    void showMenu();

    void promptUser(Action action);

    void showDetails(Pokemon pokemon);

    void clearScreen();

    void saveError(String name);

    void noPokemon();

    void showHeader(Action action);

    void showPokemonCount(int size);

    void goodbye();

    void invalidInput();

    void confirmSave(String name);

    void confirmPokemonIsFree(String name);

    void freeError(String pokemonName);

    void managementMenu();

    void pokemonNotFound();

    void checkDecision(String name, Action action);
}
