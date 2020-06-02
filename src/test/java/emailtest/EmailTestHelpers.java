package emailtest;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailTestHelpers {

    public static void signIn(@NotNull WebDriver driver) /*throws InterruptedException*/ {

        WebElement linkLogin = driver.findElement(By.linkText("Login"));
        assertTrue(linkLogin.isEnabled());
        linkLogin.click();

        WebElement textLogin = driver.findElement(By.id("login_id"));
        assertTrue(textLogin.isEnabled());
        textLogin.clear();
        textLogin.sendKeys(EmailTestConfig.getLogin());

        WebElement btnNext = driver.findElement(By.id("nextbtn"));
        assertTrue(btnNext.isEnabled());
        btnNext.click();

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        WebElement textPassword = driver.findElement(By.id("password"));
        assertTrue(textPassword.isEnabled());
        textPassword.clear();
        textPassword.sendKeys(EmailTestConfig.getPassword());

        WebElement btnSubmit = driver.findElement(By.id("nextbtn"));
        assertTrue(btnSubmit.isEnabled());
        btnSubmit.click();

    }

    public static void goToMail(@NotNull WebDriver driver) {


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement imgbtnMail = driver.findElement(By.cssSelector("._logo-mail"));

        assertTrue(imgbtnMail.isEnabled());
        imgbtnMail.click();
    }

    public static void createLetterAndSaveDraft(@NotNull WebDriver driver) {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement btnCompose = driver.findElement(By.xpath("//span[text()=\"New Mail\"]"));//(By.xpath("//*[@data-appname=\"mail\"]"));

        assertTrue(btnCompose.isEnabled());

        btnCompose.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement textTo = driver.findElement(By.xpath("//font[text()=\"To\"]/following-sibling::input"));

        assertTrue(textTo.isEnabled());

        textTo.sendKeys(EmailTestConfig.getToEmail());

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class=\"ze_area\"]")));

        WebElement emailContents = driver.findElement(By.cssSelector("body.ze_body"));

        assertTrue(emailContents.isEnabled());

        //emailContents.click();
        emailContents.sendKeys(EmailTestConfig.getMessageBody());

        driver.switchTo().defaultContent();

        WebElement btnSaveDraft = driver.findElement(By.xpath("//*[text()=\"Save Draft\"]"));

        assertTrue(btnSaveDraft.isEnabled());
        btnSaveDraft.click();
    }


    public static void checkLetterExistence(@NotNull WebDriver driver)
    {
        driver.get(driver.getCurrentUrl().replace("inbox", "drafts"));

        /*WebElement btnDrafts = driver.findElement(By.cssSelector("#\\31 448079000000002016 .zmTreeText"));
        assertTrue(btnDrafts.isEnabled());
        btnDrafts.click();*/
        String subject = driver.findElement(By.cssSelector("span.zmLSub")).getText();

        assertTrue(subject.equals(EmailTestConfig.getSubject()));
    }

    public static void signOut(@NotNull WebDriver driver)
    {
        WebElement imgAvatar = driver.findElement(By.cssSelector(".zmUserInfo > img"));
        assertTrue(imgAvatar.isEnabled());
        imgAvatar.click();

        WebElement btnLogout = driver.findElement(By.cssSelector(".zmFR"));
        assertTrue(btnLogout.isEnabled());
        btnLogout.click();
    }

}
