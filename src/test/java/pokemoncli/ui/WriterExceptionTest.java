package pokemoncli.ui;

import org.junit.Test;
import pokemoncli.consoleUI.WriterException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WriterExceptionTest {

    @Test
    public void throwsAnExceptionWithASpecificMessage() {
        RuntimeException cause = new RuntimeException("test");
        WriterException writerException = new WriterException(cause);

        assertThat(writerException.getMessage(), is("Exception when writing!"));
    }

    @Test
    public void exceptionHasOriginalCause() {
        RuntimeException cause = new RuntimeException("test");
        WriterException writerException = new WriterException(cause);

        assertThat(writerException.getCause(), is(cause));
    }
}
