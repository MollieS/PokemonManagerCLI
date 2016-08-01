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

    private void write(String message) {
        System.out.println(message);
    }

}
