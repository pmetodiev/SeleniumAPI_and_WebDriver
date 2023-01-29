import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShopLoginTest {
    WebDriver driver;

    @BeforeMethod
    public void startUp2() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://shop.pragmatic.bg/admin");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void canSuccessfullyLogInAsAdminUsingValidCredentials() {
        driver.findElement(By.xpath("//input[starts-with(@name,'username')]")).sendKeys("admin");
        driver.findElement(By.xpath("//input[starts-with(@name,'password')]")).sendKeys("parola123!");
        driver.findElement(By.cssSelector(".btn-primary")).click();

        Assert.assertTrue(driver.getTitle().equalsIgnoreCase("dashboard"), "You did not login successfully");

        driver.findElement(By.xpath("//a[contains(@href, 'logout')]")).click();
    }

    //THE BELOW TEST DOES THE SAME AS THE ONE ABOVE BUT WITHOUT THE USAGE OF THE sendKeyrs("<text>") method - I am
    //sharing it with you because one of the colegues asked about it :) - just FYI!
    @Test
    public void canSuccessfullyLogInAsAdminUsingValidCredentialsVersionTwo() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("document.getElementById('input-username').value='admin'");
        javascriptExecutor.executeScript("document.getElementById('input-password').value='parola123!'");
        driver.findElement(By.tagName("button")).click();

        WebElement logOutButton = driver.findElement(By.xpath("//a[contains(@href, 'logout')]"));
        Assert.assertEquals(logOutButton.getText(), "Logout");
        logOutButton.click();
    }
}
