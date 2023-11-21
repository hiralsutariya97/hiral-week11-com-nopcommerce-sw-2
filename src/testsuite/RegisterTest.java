package testsuite;
/**
 * 5. Write down the following test into ‘RegisterTest’ class
 * 1. userShouldNavigateToRegisterPageSuccessfully
 * * click on the ‘Register’ link
 * * Verify the text ‘Register’
 * 2. userShouldRegisterAccountSuccessfully * click on the ‘Register’ link
 * * Select gender radio button
 * * Enter First name
 * * Enter Last name
 * * Select Day Month and Year
 * * Enter Email address
 * * Enter Password
 * * Enter Confirm password
 * * Click on REGISTER button
 * * Verify the text 'Your registration completed’
 */
import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterTest extends BaseTest {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToRegisterPageSuccessfully() {
        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();
        String expectedErrorMessage = "Register";
        //Find the errorMessage element and get the text
        String actualErrorMessage = driver.findElement(By.xpath("//a[text()='Register ']")).getText();
        Assert.assertEquals("Register", expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void userShouldRegisterAccountSuccessfully() {
        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();
        WebElement Gender = driver.findElement(By.id("gender-female"));
        Gender.click();
        //Find the FirstName field
        WebElement firstnameField = driver.findElement(By.id("FirstName"));
        firstnameField.sendKeys("hiral");
        //Find LastName field
        WebElement lastNameField = driver.findElement(By.id("LastName"));
        lastNameField.sendKeys("sutariya");

        driver.findElement(By.name("DateOfBirthDay")).sendKeys("1");
        driver.findElement(By.name("DateOfBirthMonth")).sendKeys("Nov");
        driver.findElement(By.name("DateOfBirthYear")).sendKeys("1998");

        //Find the email field and type the email address to email field
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys("hiral123@gmail.com");

        //Find the password field and type the password to password field
        WebElement passwordField = driver.findElement(By.name("Password"));
        passwordField.sendKeys("hiral123");

        //find the confirmPassword field and type the password to confirmPassword field
        driver.findElement(By.name("ConfirmPassword")).sendKeys("hiral123");
        WebElement Register_button = driver.findElement(By.linkText("Register"));
        Register_button.click();
        String expectedText = "Your registration completed";
        //find the actual text element and get the text from element
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@class='result']"));
        String actualText = actualTextElement.getText();
        //Verify expected and actual text
        Assert.assertEquals("Your registration completed", expectedText, actualText);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
