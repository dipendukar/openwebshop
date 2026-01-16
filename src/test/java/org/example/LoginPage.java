package org.example;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //Locators
    private By loginLink = By.linkText("Log in");
    private By emailId = By.id("Email");
    private By passwordId = By.id("Password");
    private By loginBtn = By.cssSelector("input[value='Log in']");
    private By logoutLink = By.linkText("Log out");

    //Page Actions
    // Navigate to URL from feature file
    public void goTo(String url) {
        driver.get(url);
    }

    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
    public void enterEmailPassword(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailId)).clear();
        driver.findElement(emailId).sendKeys(email);
        driver.findElement(passwordId).clear();
        driver.findElement(passwordId).sendKeys(password);
    }
    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }
    public boolean isLoginSuccessful() {
        return !driver.findElements(logoutLink).isEmpty();
    }
    public void logoutAndReturnToLogin() {
        List<WebElement> logout = driver.findElements(logoutLink);
        if (!logout.isEmpty()) {
            logout.get(0).click();

            wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
            waitForLoginPage();
        }
    }
    /** Wait until the login page is ready (email field visible) */
    public void waitForLoginPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailId));
    }

}
