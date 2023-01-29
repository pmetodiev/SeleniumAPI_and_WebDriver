import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class FailShopLogginTest {

    WebDriver driver;

    @BeforeMethod
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://shop.pragmatic.bg/admin");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void cantLogInUsingIncorrectCredentials() {
        driver.findElement(By.xpath("//input[starts-with(@name,'username')]")).sendKeys("admin1");
        driver.findElement(By.xpath("//input[starts-with(@name,'password')]")).sendKeys("parola1");
        driver.findElement(By.cssSelector(".btn-primary")).click();
        WebElement fail = driver.findElement(By.cssSelector("div.alert-danger"));

        assertTrue(fail.getText().contains("No match for Username and/or Password."));
    }
}
