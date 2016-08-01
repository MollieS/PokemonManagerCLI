package pkmnman;

import pkmncore.Pokemon;

public interface Display {

    void greet();

    void promptUser();

    void showDetails(Pokemon pokemon);

    void clearScreen();
}
