package pokemoncli.ui;

import pokemoncli.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputFake implements Input {

    private List<String> input = new ArrayList();

    public void set(String... words) {
        Collections.addAll(input, words);
    }

    public String get() {
        return input.remove(0);
    }
}
