package pkmnman;

import pkmncore.Pokemon;

public class Script {

    public String greet() {
        return "---------------------------" + "\n" +
                "Welcome to Pokemon Manager" + "\n" +
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
}
