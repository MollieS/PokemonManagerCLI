package pkmnman;

import pkmncore.Pokemon;

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
        outputStream += script.greet();
    }

    public void showMenu() {
        outputStream += script.showMenu();
    }

    public void showSearchHeader() {
        outputStream += script.searchPage();
    }

    public void showAddHeader() {
        outputStream += script.addingPage();
    }

    public void promptForPokemon() {
        outputStream += script.promptUserForCaughtPokemon();
    }

    public void askForSave() {
        outputStream += script.askForConfirmation();
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

    public void invalidPokemon(String message) {
        outputStream += script.saveError(message);
    }

    public void noPokemon() {
        outputStream += script.noCaughtPokemon();
    }

    public void showViewHeader() {
        outputStream += script.viewPage();
    }

    public void askForCatch() {
        outputStream += script.askForCatch();
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
}
