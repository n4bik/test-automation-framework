package pl.tomaszbuga;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FirstTest {
    @Test()
    public void firstMethod() {
        //Assign
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Act
        driver.get("https://bing.com");
        driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
        //Assert
        WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3")));
        System.out.println(firstResult.getText());
        driver.quit();
    }
}
