package pkmnman;

import pkmncore.Pokemon;

public class MenuPage implements Page {

    private Display display;
    private Input input;
    private Message message;

    public MenuPage(Display display, Input input) {
        this.display = display;
        this.input = input;
    }

    public Action view(Message messageToDisplay) {
        showPageContent(messageToDisplay);
        String answer = input.get().toUpperCase().trim();
        if (actionIsValid(answer)) return Action.valueOf(answer);
        message = Message.INPUTERROR;
        return Action.NONE;
    }

    private void showPageContent(Message messageToDisplay) {
        display.clearScreen();
        display.greet();
        displayMessage(messageToDisplay);
        display.showMenu();
    }

    private boolean actionIsValid(String answer) {
        for (Action action : Action.values()) {
            if (getAction(answer, action)) return true;
        }
        message = Message.INPUTERROR;
        return false;
    }

    private boolean getAction(String answer, Action action) {
        if (action.toString().equals(answer)) {
            message = Message.NONE;
            return true;
        }
        return false;
    }

    private void displayMessage(Message messageToDisplay) {
        if (messageToDisplay == Message.INPUTERROR) {
            display.invalidInput();
        } else if (messageToDisplay == Message.NOTFOUND) {
            display.pokemonNotFound();
        }
    }
    public Message getMessage() {
        return message;
    }

    public Pokemon getPokemon() {
        return Pokemon.NULL;
    }
}
