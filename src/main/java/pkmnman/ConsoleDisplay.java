package pkmnman;

import pkmncore.Pokemon;

public class ConsoleDisplay implements Display {

    private final Script script;

    public ConsoleDisplay(Script script) {
        this.script = script;
    }

    public void greet() {
        write(script.greet());
    }

    public void showMenu() {
        write(script.showMenu());
    }

    public void showSearchHeader() {
        write(script.searchPage());
    }

    public void showAddHeader() {
        write(script.addingPage());
    }

    public void promptForPokemon() {
        write(script.promptUserForCaughtPokemon());
    }

    public void askForSave() {
        write(script.askForConfirmation());
    }

    public void promptUser() {
        write(script.promptUser());
    }

    public void showDetails(Pokemon pokemon) {
        write(script.showDetails(pokemon));
    }

    public void clearScreen() {
        write("\033[H\033[2J");
        System.out.flush();
    }

    public void confirmSave(String name) {
        write(script.confirmSave(name));
    }

    public void invalidPokemon(String message) {
        write(script.saveError(message));
    }

    public void noPokemon() {
        write(script.noCaughtPokemon());
    }

    public void showViewHeader() {
        write(script.viewPage());
    }

    public void askForCatch() {
        write(script.askForCatch());
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

    public void checkIfCaught(String name) {
        write(script.checkIfCaught(name));
    }

    private void write(String message) {
        System.out.println(message);
    }

}
