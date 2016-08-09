package pokemoncli.ui;

import pokemoncli.Display;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Action;
import pokemonmanager.Pokemon;

public class DisplayFake implements Display {

    private final Script script;
    private String outputStream = "";
    
    public DisplayFake(Script script) {
       this.script = script; 
    }

    public String read() {
        return outputStream;
    }

    public void greet() {
        outputStream += script.menuHeader();
    }

    public void showMenu() {
        outputStream += script.showMenu();
    }

    public void showSearchHeader() {
        outputStream += script.header(Action.SEARCH);
    }

    public void showFreeHeader() {
        outputStream += script.freePage();
    }

    public void promptUser() {
        outputStream += script.promptUser();
    }

    public void showDetails(Pokemon pokemon) {
        outputStream += script.showDetails(pokemon);
    }

    public void clearScreen() {
    }

    public void confirmSave(String name) {
       outputStream += script.confirmSave(name);
    }

    public void saveError(String name) {
        outputStream += script.saveError(name);
    }

    public void noPokemon() {
        outputStream += script.noCaughtPokemon();
    }

    public void showHeader(Action action) {
        outputStream += script.header(action);
    }

    public void showViewHeader() {
        outputStream += script.viewPage();
    }

    public void showPokemonCount(int size) {
        outputStream += script.showCount(size);
    }

    public void goodbye() {
        outputStream += script.goodbye();
    }

    public void invalidInput() {
        outputStream += script.invalidInput();
    }

    public void checkIfCaught(String name) {
        outputStream += script.checkIfCaught(name);
    }

    public void promptForNameToFree() {
        outputStream += script.askForFreeName();
    }

    public void confirmFreedom(String name) {
        outputStream += script.confirmFreedom(name);
    }

    public void confirmPokemonIsFree(String name) {
        outputStream += script.displayFreedom(name);
    }

    public void managementMenu() {
        outputStream += script.managementMenu();
    }

    public void freeError(String pokemonName) {
        outputStream += script.freeError(pokemonName);
    }

    public void pokemonNotFound() {
        outputStream += script.pokemonNotFound();
    }
}
