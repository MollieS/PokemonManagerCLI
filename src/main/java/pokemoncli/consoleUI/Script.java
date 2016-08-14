package pokemoncli.consoleUI;

import pokemoncli.navigation.Action;
import pokemonmanager.Pokemon;

public class Script {

    public String menuHeader() {
        return format("WELCOME TO POKEMON MANAGER");
    }

    public String header(Action action) {
        return format("POKEMON " + action);
    }

    private String format(String phrase) {
        return "---------------------------" + "\n" +
                phrase + "\n" +
                "---------------------------" + "\n";
    }

    public String promptUser(Action action) {
        return "Please enter the name of the pokemon you wish to " + action + ":" + "\n";
    }

    public String showDetails(Pokemon pokemon) {
        String details = showName(pokemon);
        if (pokemon.equals(Pokemon.NULL)) {
            return details;
        }
        details = getDetails(pokemon, details);
        return details;
    }

    private String getDetails(Pokemon pokemon, String details) {
        details += "|  height: " + pokemon.getHeight() + "\n" +
                "|  abilities: " + "\n";
        details = getAbilities(pokemon, details);
        details += "------------------------------" + "\n";
        return details;
    }

    private String showName(Pokemon pokemon) {
        return format("|  name:   " + pokemon.getName().toUpperCase());
    }

    private String getAbilities(Pokemon pokemon, String details) {
        for (String ability : pokemon.getAbilities()) {
            details += "      " + ability + "\n";
        }
        return details;
    }

    public String showMenu() {
        return "What would you like to do?" + "\n" +
                "You can:" + "\n" +
                Action.SEARCH + " for a pokemon" + "\n" +
                Action.MANAGE + " your pokemon" + "\n" +
                Action.QUIT + " Pokemon Manager" + "\n";
    }

    public String promptUserForCaughtPokemon() {
        return "Which pokemon did you catch?" + "\n";
    }

    public String confirmSave(String name) {
        return name + " was caught!" + "\n";
    }

    public String displayFreedom(String name) {
        return name + " has been set free!" + "\n";
    }

    public String showCount(int count) {
        return "you have caught " + count + " pokemon" + "\n";
    }

    public String askForConfirmation() {
        return "Is this the pokemon you caught?" + "\n" +
                "yes or no" + "\n";
    }

    public String saveError(String name) {
        return name + " has already been caught!" + "\n";
    }

    public String freeError(String name) {
        return name + " has not been caught!" + "\n";
    }

    public String noCaughtPokemon() {
        return "You haven't caught any pokemon!" + "\n";
    }

    public String askForCatch() {
        return "Would you like to add a pokemon you have caught?" + "\n";
    }

    public String goodbye() {
        return "Goodbye!" + "\n";
    }

    public String invalidInput() {
        return "That answer didn't seem to be valid" + "\n";
    }

    public String checkIfCaught(String name) {
        return "Would you like to add " + name + " to your caught pokemon collection?" + "\n" +
                "yes or no" + "\n";
    }

    public String confirmFreedom(String name) {
        return "Are you sure you want to set " + name + " free?" + "\n" +
                "yes or no" + "\n";
    }

    public String managementMenu() {
        return "What would you like to do?" + "\n" +
                "You can: " + "\n" +
                Action.ADD + " a pokemon" + "\n" +
                Action.FREE + " a pokemon" + "\n" +
                Action.QUIT + " Pokemon Manager" + "\n" +
                "or go to main " + Action.MENU + "\n";
    }

    public String pokemonNotFound() {
        return "This pokemon does not exist!";
    }
}
