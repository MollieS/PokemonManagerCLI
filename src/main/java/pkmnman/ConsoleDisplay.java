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

    public void showFreeHeader() {
        write(script.freePage());
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

    public void saveError(String name) {
        write(script.saveError(name));
    }

    public void noPokemon() {
        write(script.noCaughtPokemon());
    }

    public void showViewHeader() {
        write(script.viewPage());
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

    public void promptForNameToFree() {
        write(script.askForFreeName());
    }

    public void confirmFreedom(String name) {
        write(script.confirmFreedom(name));
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
        System.out.println(message);
    }

}
