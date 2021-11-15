package pl.tomaszbuga.pom;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void getTomaszbugaHomepage() {
        driver.get("http://www.tomaszbuga.pl");
    }
}
