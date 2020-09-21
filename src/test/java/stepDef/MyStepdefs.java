package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

public class MyStepdefs {
    private ChromeDriver driver;

    @Given("the user wants go to the url {string}")
    public void theUserWantsGoToTheUrl(String url) {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setHeadless(true);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @When("Enter valid username {string} and pass {string}")
    public void enterValidUsernameAndPass(String user, String pass) throws InterruptedException {
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[@aria-controls='wt-locale-picker-overlay']")));
        driver.findElement(By.xpath("//button[@aria-controls='wt-locale-picker-overlay']")).click();
        new Select(driver.findElement(By.id("locale-overlay-select-language_code"))).selectByValue("en-US");
        driver.findElement(By.id("locale-overlay-save")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[contains(@class, 'select-signin')]")).click();
        driver.findElement(By.id("join_neu_email_field")).sendKeys(user);
        driver.findElement(By.id("join_neu_password_field")).sendKeys(pass);
        driver.findElement(By.xpath("//div[@class='col-group col-flush']//button[@value='sign-in']")).click();
        Thread.sleep(3000);
    }

    @Then("unsuccessful Login")
    public void unsuccessfulLogin() {
        String message = driver.findElement(By.id("aria-join_neu_password_field-error")).getText();
        Assert.assertEquals("Password was incorrect.", message);
        driver.quit();
    }
}
