import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login {
    WebDriver driver;


    @BeforeTest
    public void openBrowser() {
        driver = new EdgeDriver();
        driver.get("https://www.saucedemo.com/");

    }

    @Test(priority = 1) //1-Check if the username and password fields are on the main screen of the application:
    public void loginPageContents() {

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement pass = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        Assert.assertTrue(username.isDisplayed(), "Username not found");
        Assert.assertTrue(pass.isDisplayed(), "Password not found");
        Assert.assertTrue(loginButton.isDisplayed(), "Login Button not found");
    }

    @Test(priority = 2) //2. Check if the given valid credentials work:
    public void validTestLogin() {

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement pass = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        username.sendKeys("standard_user");
        pass.sendKeys("secret_sauce");
        loginButton.click();
        WebElement swagLabsText = driver.findElement(By.className("app_logo"));
        Assert.assertTrue(swagLabsText.isDisplayed(), "swage labs is not visible");
        driver.navigate().back();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }

    @Test(priority = 4) //3. Check if the given wrong credentials work:
    public void inValidTestLogin() {

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement pass = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        username.clear();
        pass.clear();
        username.sendKeys("username");
        pass.sendKeys("password");
        loginButton.click();
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\'login_button_container\']/div/form/div[3]/h3"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Not Visible Error Message");
        Assert.assertTrue(errorMessage.getText().contains("Epic sadface: Username and password do not match any user in this service"),
                "Not Match error message expected and acual");

    }

    @Test(priority = 3) //4. Check for empty credentials:
    public void emptyTestLogin() {

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement pass = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));
        username.clear();
        pass.clear();
        loginButton.click();
        WebElement errorMessage_username = driver.findElement(By.xpath("//*[@id=\'login_button_container\']/div/form/div[3]/h3"));
        Assert.assertTrue(errorMessage_username.isDisplayed(), "Not Visible Error Message");
        Assert.assertTrue(errorMessage_username.getText().contains("Epic sadface: Username is required"), "Not match");
        username.sendKeys("standard_user");
        loginButton.click();
        WebElement errorMessage_Pass = driver.findElement(By.xpath("//*[@id=\'login_button_container\']/div/form/div[3]/h3"));
        Assert.assertTrue(errorMessage_Pass.isDisplayed(), "Not visible Error message");
        Assert.assertTrue(errorMessage_Pass.getText().contains("Epic sadface: Password is required"));

    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}
