package pokemoncli.consoleUI;

import pokemoncli.Input;

import java.util.Scanner;

public class ConsoleInput implements Input {

    public String get() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
