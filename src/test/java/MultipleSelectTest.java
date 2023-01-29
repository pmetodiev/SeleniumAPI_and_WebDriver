import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultipleSelectTest {

    WebDriver driver;

    @BeforeMethod
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkingTheMultipleSelectedOptions() {

        Select allColors = new Select(driver.findElement(By.name("color")));

        Actions builder = new Actions(driver);

        builder
                .keyDown(Keys.CONTROL)
                .click(driver.findElement(By.xpath("//option[@value='rd']")))
                .click(driver.findElement(By.xpath("//option[@value='sl']")))
                .keyUp(Keys.CONTROL)
                .perform();

        List<WebElement> allSelectedOptions = allColors.getAllSelectedOptions();
        List<String> actualSelectedOptions = new ArrayList<>();

        for (WebElement curSelectedOption : allSelectedOptions) {
            actualSelectedOptions.add(curSelectedOption.getText());
        }

        List<String> expectedSelectedOptions = Arrays.asList("Red", "Silver");

        Assert.assertEquals(expectedSelectedOptions, actualSelectedOptions);
    }
}
