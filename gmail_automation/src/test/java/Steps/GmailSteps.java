package Steps;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class GmailSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    @Given("I am on the Gmail login page")
    public void iAmOnTheGmailLoginPage() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("https://mail.google.com");
    }

    @When("I log in with username {string} and password {string}")
    public void iLogInWithUsernameAndPassword(String username, String password) {
        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
        emailInput.sendKeys(username);
        emailInput.sendKeys(Keys.ENTER);

        WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.titleContains("Inbox"));
    }

    @Then("I compose a new email with subject {string} and body content")
    public void iComposeANewEmailWithSubjectAndBodyContent(String subject, String body) {
        WebElement composeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Compose']")));
        composeButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("to")));
        WebElement toInput = driver.findElement(By.name("to"));
        toInput.sendKeys("recipient.email@example.com");

        WebElement subjectInput = driver.findElement(By.name("subjectbox"));
        subjectInput.sendKeys(subject);

        WebElement bodyInput = driver.findElement(By.xpath("//div[@role='textbox']"));
        bodyInput.sendKeys(body);
    }

    @And("I send the email")
    public void iSendTheEmail() {
        WebElement sendButton = driver.findElement(By.xpath("//div[contains(text(),'Send')][@role='button']"));
        sendButton.click();
    }

    @Then("I should see the email sent confirmation")
    public void iShouldSeeTheEmailSentConfirmation() {
        // Optionally, add verification steps for sent confirmation if applicable
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}