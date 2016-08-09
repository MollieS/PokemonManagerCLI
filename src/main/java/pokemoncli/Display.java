package pokemoncli;

import pokemoncli.navigation.Action;
import pokemonmanager.Pokemon;

public interface Display {

    void greet();

    void showMenu();

    void showSearchHeader();

    void promptUser();

    void showDetails(Pokemon pokemon);

    void clearScreen();

    void confirmSave(String name);

    void saveError(String name);

    void noPokemon();

    void showHeader(Action action);

    void showViewHeader();

    void showPokemonCount(int size);

    void goodbye();

    void invalidInput();

    void checkIfCaught(String name);

    void promptForNameToFree();

    void confirmFreedom(String name);

    void confirmPokemonIsFree(String name);

    void managementMenu();

    void freeError(String pokemonName);

    void pokemonNotFound();
}
