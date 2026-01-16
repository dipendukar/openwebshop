package org.example;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class stepDefClass {

    private LoginPage loginPage = new LoginPage(HookClass.getDriver());
    private static final Logger log = LogManager.getLogger(stepDefClass.class);


    @Given("user navigate to site url {string}")
    public void user_navigate_to_site_url(String url) {
        loginPage.goTo(url);
        log.info("Navigated to site url");
        loginPage.clickLoginLink();
        log.info("login link is clicked");
    }
    @Then("verify login page title {string}")
    public void verify_login_page_title(String title) {
        String actualTitle = HookClass.getDriver().getTitle();
        Assert.assertEquals(actualTitle,title);
        log.info("Verify login page title"+title);
    }
    @When("user login to site with multiple credentials")
    public void user_login_to_site_with_multiple_credentials(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String,String>> credentials = dataTable.asMaps(String.class,String.class);

        for(Map<String,String> map : credentials){
            String username = map.get("username");
            String password = map.get("password");

            log.info("The username is passed: "+username+"The password is passed: "+password);
            loginPage.enterEmailPassword(username,password);
            loginPage.clickLoginBtn();

            if(loginPage.isLoginSuccessful()) {
                // Mark scenario as failed
                loginPage.logoutAndReturnToLogin();
                log.info("Logout successful now return to login page");
            }else{
                Assert.fail("Logout link not found — login might have failed for user: " + username);
            }
            // Wait for login page ready before next login
            loginPage.waitForLoginPage();
        }

    }
}
