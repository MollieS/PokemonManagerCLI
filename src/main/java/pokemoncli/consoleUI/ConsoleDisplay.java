package pokemoncli.consoleUI;

import pokemoncli.Display;
import pokemoncli.navigation.Action;
import pokemonmanager.Pokemon;

import java.io.Writer;

public class ConsoleDisplay implements Display {

    private final Script script;
    private final Writer writer;

    public ConsoleDisplay(Script script, Writer writer) {
        this.script = script;
        this.writer = writer;
    }

    public void greet() {
        write(script.menuHeader());
    }

    public void showMenu() {
        write(script.showMenu());
    }

    public void promptUser(Action action) {
        write(script.promptUser(action));
    }

    public void showDetails(Pokemon pokemon) {
        write(script.showDetails(pokemon));
    }

    public void clearScreen() {
        write("\033[H\033[2J");
    }

    public void confirmSave(String name) {
        write(script.confirmSave(name));
    }

    public void saveError(String name) {
        write(script.saveError(name));
    }

    public void noPokemon() {
        write(script.noCaughtPokemon());
    }

    public void showHeader(Action action) {
        write(script.header(action));
    }

    public void showPokemonCount(int size) {
        write(script.showCount(size));
    }

    public void goodbye() {
        write(script.goodbye());
    }

    public void invalidInput() {
        write(script.invalidInput());
    }

    public void checkDecision(String name, Action action) {
        write(script.checkUserAction(name, action));
    }

    public void confirmPokemonIsFree(String name) {
        write(script.displayFreedom(name));
    }

    public void managementMenu() {
        write(script.managementMenu());
    }

    public void freeError(String pokemonName) {
        write(script.freeError(pokemonName));
    }

    public void pokemonNotFound() {
        write(script.pokemonNotFound());
    }

    private void write(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (Exception e) {
            throw new WriterException(e);
        }
    }
}
