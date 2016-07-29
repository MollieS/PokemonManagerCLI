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

    public void promptUser() {
        outputStream += script.promptUser();
    }

    public void showDetails(Pokemon pokemon) {
        outputStream += script.showDetails(pokemon);
    }

    public void clearScreen() {
    }
}
