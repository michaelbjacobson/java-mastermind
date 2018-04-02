import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CodeCreatorTest {

    private CodeCreator subject;

    @Before
    public void setUp() {
        subject = new CodeCreator();
    }

    @Test
    public void generatedCodeIsFourCharsLong() {
        String code = subject.newCode();
        assertEquals(4, code.length());
    }

    @Test
    public void generatedCodeDigitsAreBetweenZeroAndFive() {
        String code = subject.newCode();
        assertTrue(code.matches("[012345]{4}"));
    }

}