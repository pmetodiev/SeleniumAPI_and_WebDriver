import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdersDropDownTest {
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
    public void checkingTheOrdersTypeDropDownOptions() {
        driver.findElement(By.xpath("//input[starts-with(@name,'username')]")).sendKeys("admin");
        driver.findElement(By.xpath("//input[starts-with(@name,'password')]")).sendKeys("parola123!");
        driver.findElement(By.cssSelector(".btn-primary")).click();

        driver.findElement(By.id("menu-sale")).click();
        driver.findElement(By.linkText("Orders")).click();

        Select orderStatusDropDown = new Select(driver.findElement(By.id("input-order-status")));

        List<String> expectedValues = Arrays.asList(
                "",
                "Missing Orders",
                "Canceled",
                "Canceled Reversal",
                "Chargeback",
                "Complete",
                "Denied",
                "Expired",
                "Failed",
                "Pending",
                "Processed",
                "Processing",
                "Refunded",
                "Reversed",
                "Shipped",
                "Voided");

        List<String> actualValues = new ArrayList<>();
        List<WebElement> allDropdownElements = orderStatusDropDown.getOptions();

        for (WebElement curDropdownElement : allDropdownElements) {
            actualValues.add(curDropdownElement.getText());
        }

        Assert.assertEquals(expectedValues, actualValues);

        driver.findElement(By.xpath("//a[contains(@href, 'logout')]")).click();
    }
}
