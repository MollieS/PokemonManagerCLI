package pkmnman;

import pkmncore.Pokemon;

public interface Display {

    void greet();

    void showMenu();

    void showSearchHeader();

    void showAddHeader();

    void promptForPokemon();

    void askForSave();

    void promptUser();

    void showDetails(Pokemon pokemon);

    void clearScreen();

    void confirmSave(String name);

    void invalidPokemon(String message);

    void noPokemon();

    void showViewHeader();

    void askForCatch();

    void showPokemonCount(int size);

    void goodbye();

    void invalidInput();

    void checkIfCaught(String name);
}
