package testsuite;
/**
 2. Create the package ‘testsuite’ and create the following classes inside the ‘testsuite’ package.
 1. LoginTest
 2. TopMenuTest
 3. RegisterTest
 3. Write down the following test into ‘LoginTest’ class
 1. userShouldNavigateToLoginPageSuccessfully * click on the ‘Login’ link
 * Verify the text ‘Welcome, Please Sign In!’
 2. userShouldLoginSuccessfullyWithValidCredentials
 * click on the ‘Login’ link
 * Enter valid username
 * Enter valid password
 * Click on ‘LOGIN’ button
 * Verify the ‘Log out’ text is display
 3. verifyTheErrorMessage
 * click on the ‘Login’ link
 * Enter invalid username
 * Enter invalid password
 * Click on Login button
 * Verify the error message ‘Login was unsuccessful.
 Please correct the errors and try again. No customer account found’
 *
 */

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        //Find the login link and click on it
        WebElement loginlink = driver.findElement(By.linkText("Log in"));
        loginlink.click();
        String expectedText = "Welcome, Please Sign In!";
        //Find the actual text element and get the text from element
        WebElement actualTextElement = driver.findElement(By.xpath("//h1"));
        String actualText = actualTextElement.getText();
        //Verify expected and actual text
        Assert.assertEquals("Not redirected to login page", expectedText, actualText);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //Find the login link and click on it
        WebElement loginlink = driver.findElement(By.linkText("Log in"));
        loginlink.click();
        //Find the email field and type the email address to email field
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys("hiral123@gmail.com");

        //Find the password field and type the password to password field
        driver.findElement(By.name("Password")).sendKeys("hiral123");
        //Find the login btn element and click
        driver.findElement(By.xpath("//button[text() ='Log in']")).click();

        String expectedErrorMessage = "log out";
        //Find the errorMessage element and get the text
        String actualErrorMessage = driver.findElement(By.className("ico-logout")).getText();
        Assert.assertEquals("Correct Message not Displayed", expectedErrorMessage, actualErrorMessage);

    }

    @Test
    public void verifyTheErrorMessage() {
        //Find the login link and click on it
        WebElement loginlink = driver.findElement(By.linkText("Log in"));
        loginlink.click();

        //Find the email field and type the email address to email field
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys("abc123@gmail.com");

        //Find the password field and type the password to password field
        driver.findElement(By.name("Password")).sendKeys("abc123");

        //find the login btn element and click
        driver.findElement(By.xpath("//button[(text() = 'Log in')]")).click();

        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
                " No customer account found";
        //find the errorMessage element and get the text
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']")).getText();
        Assert.assertEquals("Correct message not display", expectedErrorMessage, actualErrorMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
