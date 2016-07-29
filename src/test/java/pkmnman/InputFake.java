package pkmnman;

import java.util.ArrayList;
import java.util.List;

public class InputFake implements Input {

    private List<String> input = new ArrayList();

    public void set(String words) {
        input.add(words);
    }

    public String get() {
        return input.remove(0);
    }
}
