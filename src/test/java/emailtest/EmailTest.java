package emailtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setupDriver() {

        switch (EmailTestConfig.getBrowser()) {
            case Firefox:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case Chrome:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case Chromium:
                WebDriverManager.chromiumdriver().setup();
                driver = new ChromeDriver();
                break;

            case Edge:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case Opera:
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;

            case IE:
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;

            default:
                throw new IllegalStateException("Unexpected browser type: " + EmailTestConfig.getBrowser().name());
        }

    }

    @BeforeEach
    public void goToHomepage() {
        driver.get(EmailTestConfig.getHomeURL());
        assertTrue(driver.getTitle().startsWith(EmailTestConfig.getServiceName()));
    }

    @Test
    public void letterCreationAndDraftsTest() throws InterruptedException {
        EmailTestHelpers.signIn(driver);
        EmailTestHelpers.goToMail(driver);
        EmailTestHelpers.createLetterAndSaveDraft(driver);
        EmailTestHelpers.signOut(driver);
    }

    @Test
    public void draftContentsCheck() throws InterruptedException {
        EmailTestHelpers.signIn(driver);
        EmailTestHelpers.goToMail(driver);
        EmailTestHelpers.checkLetterExistence(driver);
        EmailTestHelpers.signOut(driver);
    }

    @AfterEach
    public void closeBrowser() {
        driver.close();
    }

}
