package pokemoncli.consoleUI;

public class WriterException extends RuntimeException {

    public WriterException(Throwable cause) {
        super("Exception when writing!", cause);
    }
}
