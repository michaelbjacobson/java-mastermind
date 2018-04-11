import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class Slow_IntegrationTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUpAll() throws InterruptedException {
        System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox");
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);

        String testCode = "0123";
        CodeCreator codeCreatorMock = mock(CodeCreator.class);
        when(codeCreatorMock.newCode()).thenReturn(testCode);

        Controller.run(new Mastermind(codeCreatorMock));
        TimeUnit.SECONDS.sleep(1);
    }

    @AfterClass
    public static void tearDownAll() {
        Controller.kill();
        driver.quit();
    }

    @Before
    public void setUpEach() {
        if ( activeAlertIsPresent() ) { driver.switchTo().alert().accept(); }
        driver.get("http://127.0.0.1:4567/");
    }

    @Test
    public void userCanAddColoredCodePegsToTheActiveGuess() {
        clickOnWebElement("div.color.gold", 4);
        assertEquals(4, countWebElement("div.code_peg.gold"));
    }

    @Test
    public void userCanClearTheActiveGuess() {
        clickOnWebElement("div.color.cyan", 4);
        clickOnWebElement("button#clear", 1);
        assertEquals(0, countWebElement("div.code_peg.cyan"));
    }

    @Test
    public void codeSolutionIsHiddenByDefault() {
        assertEquals(0, countWebElement("div.solution_peg.visible"));
    }

    @Test
    public void userCanCheatAndSeeSolution() {
        clickOnWebElement("button#solve", 1);
        assertEquals(4, countWebElement("div.solution_peg.visible"));
    }

    @Test
    public void whollyCorrectGuessReturnsFourBlackKeyPegs() {
        submitWinningGuess();
        assertEquals(4, countWebElement("div.key_peg.correct"));
        assertEquals(0, countWebElement("div.key_peg.colorMatch"));
    }

    @Test
    public void whollyIncorrectGuessReturnsNoKeyPegs() {
        clickOnWebElements(new String[] {"div.color.purple", "div.color.orange"}, 2);
        clickOnWebElement("button#submit", 1);
        assertEquals(0, countWebElement("div.key_peg.correct"));
        assertEquals(0, countWebElement("div.key_peg.colorMatch"));
    }

    @Test
    public void incorrectGuessWithFourMatchingColorsReturnsFourGreyKeyPegs() {
        String[] selectorsToClick = new String[] {
                "div.color.blue", "div.color.magenta",
                "div.color.cyan", "div.color.gold",
                "button#submit"
        };
        clickOnWebElements(selectorsToClick, 1);
        assertEquals(0, countWebElement("div.key_peg.correct"));
        assertEquals(4, countWebElement("div.key_peg.colorMatch"));
    }

    @Test
    public void userCanStartANewGame() {
        String[] selectorsToClick = new String[] {
                "div.color.blue", "div.color.magenta",
                "div.color.cyan", "div.color.gold",
                "button#submit",
        };
        clickOnWebElements(selectorsToClick, 1);
        clickOnWebElement("button#new_game", 1);
        assertEquals(0, countWebElement("div.solution_peg.visible"));
        assertEquals(0, countWebElement("div.key_peg.correct"));
        assertEquals(0, countWebElement("div.key_peg.colorMatch"));
    }

    @Test
    public void userIsAlertedIfTheyHaveWon() {
        submitWinningGuess();
        String alertMessage = driver.switchTo().alert().getText();
        String expectedAlertMessage = "You cracked it!";
        assertEquals(expectedAlertMessage, alertMessage);
    }

    @Test
    public void userIsAlertedIfTheyHaveLost() {
        playLosingGame();
        String alertMessage = driver.switchTo().alert().getText();
        String expectedAlertMessage = "Game over!";
        assertEquals(expectedAlertMessage, alertMessage);
    }

    @Test
    public void solutionBecomesVisibleIfUserWins() {
        submitWinningGuess();
        assertEquals(4, countWebElement("div.solution_peg.visible"));
    }

    @Test
    public void solutionBecomesVisibleIfUserLoses() {
        playLosingGame();
        assertEquals(4, countWebElement("div.solution_peg.visible"));
    }



//  Test Helpers  //////////////////////////////////////////////////////////////////////////////////////////////////////

    private void clickOnWebElements(String[] cssSelectors, int timesToClick) {
        for (String selector: cssSelectors) {
            clickOnWebElement(selector, timesToClick);
        }
    }

    private void clickOnWebElement(String cssSelector, int timesToClick) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        for (int i = 0; i < timesToClick; i++) {
            element.click();
        }
    }

    private void submitWinningGuess() {
        String[] selectorsToClick = new String[] {
                "div.color.gold", "div.color.cyan",
                "div.color.magenta", "div.color.blue",
                "button#submit"
        };
        clickOnWebElements(selectorsToClick, 1);
    }

    private void playLosingGame() {
        for (int i = 0; i < 10; i++) {
            clickOnWebElement("div.color.purple", 4);
            clickOnWebElement("button#submit", 1);
        }
    }

    private int countWebElement(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector)).size();
    }

    private boolean activeAlertIsPresent() {
        try {
            driver.switchTo().alert();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

}
