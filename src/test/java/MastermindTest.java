import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MastermindTest {

    private CodeCreator codeCreator;
    private Mastermind subject;
    private String sampleCode = "0123";

    @Before
    public void setUp() {
        codeCreator = mock(CodeCreator.class);
        when(codeCreator.newCode()).thenReturn(sampleCode);
        subject = new Mastermind(codeCreator);
    }

    @Test
    public void willCallForNewCodeOnInstantiation() {
        verify(codeCreator).newCode();
    }

    @Test
    public void canReturnCode() {
        String code = subject.getCode();
        assertEquals(code, sampleCode);
    }

    @Test
    public void willCallForFreshCodeOnReset() {
        subject.reset();
        verify(codeCreator, times(2)).newCode();
    }

    @Test
    public void willReturnAllCorrectsIfAllColorsAreCorrectAndInCorrectOrder() {
        String feedback = subject.guess(sampleCode);
        String expectedResult = "correct,correct,correct,correct";
        assertEquals(expectedResult, feedback);
    }

    @Test
    public void willReturnEmptyStringIfAllGuessesAreIncorrectAndNoColorsMatch() {
        String incorrectCode = "5555";
        String feedback = subject.guess(incorrectCode);
        String expectedResult = "";
        assertEquals(expectedResult, feedback);
    }

    @Test
    public void willReturnAllColorMatchesIfColorsAreCorrectButInWrongOrder() {
        String incorrectOrderCode = "3201";
        String feedback = subject.guess(incorrectOrderCode);
        String expectedResult = "colorMatch,colorMatch,colorMatch,colorMatch";
        assertEquals(expectedResult, feedback);
    }

    @Test
    public void willNotReturnColorMatchesForColorsAlreadyMarkedAsCorrect() {
        String incorrectOrderCode = "2323";
        String feedback = subject.guess(incorrectOrderCode);
        String expectedResult = "correct,correct";
        assertEquals(expectedResult, feedback);
    }

}
