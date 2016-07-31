package pkmnman;

import pkmncore.Pokemon;

public class Script {

    public String greet() {
        return "---------------------------" + "\n" +
                "WELCOME TO POKEMON MANAGER" + "\n" +
                "---------------------------" + "\n";
    }

    public String searchPage() {
        return "---------------------------" + "\n" +
                "POKEMON SEARCH" + "\n" +
                "---------------------------" + "\n";
    }

    public String addingPage() {
        return "---------------------------" + "\n" +
                "ADD A POKEMON" + "\n" +
                "---------------------------" + "\n";
    }

    public String viewPage() {
        return "---------------------------" + "\n" +
                "YOUR POKEMON" + "\n" +
                "---------------------------" + "\n";
    }


    public String promptUser() {
        return "Please enter the name of the pokemon you wish to find:" + "\n";
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
        return "------------------------------" + "\n" +
                    "|  name:   " + pokemon.getName().toUpperCase() + "\n" +
                    "------------------------------" + "\n";
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
                "SEARCH for a pokemon" + "\n" +
                "ADD a pokemon you have caught" + "\n" +
                "VIEW the pokemon you have caught" + "\n" +
                "QUIT Pokemon Manager" + "\n";
    }

    public String promptUserForCaughtPokemon() {
        return "Which pokemon did you catch?";
    }

    public String confirmSave(String name) {
        return name + " was caught!";
    }

    public String showCount(int count) {
        return "you have caught " + count + " pokemon";
    }

    public String askForConfirmation() {
        return "Is this the pokemon you caught?";
    }

    public String saveError(String message) {
        return "Something went wrong: " + message;
    }

    public String noCaughtPokemon() {
        return "You haven't caught any pokemon!";
    }

    public String askForCatch() {
        return "Would you like to add a pokemon you have caught?";
    }

    public String goodbye() {
        return "Goodbye!";
    }

    public String invalidInput() {
        return "That answer didn't seem to be valid";
    }

    public String checkIfCaught(String name) {
        return "Would you like to add " + name + " to your caught pokemon collection?";
    }
}
