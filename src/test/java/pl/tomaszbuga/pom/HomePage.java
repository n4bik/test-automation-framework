package pl.tomaszbuga.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pl.tomaszbuga.utils.WaitForElement;

public class HomePage extends BasePage {
    @FindBy(how = How.CLASS_NAME, using = "td-search-input")
    private WebElement searchField;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void seleniumStaleElementTest() {
        driver.get("http://www.selenium.dev");
        searchField.sendKeys("Remote", Keys.ENTER);

        // We're waiting after search for DOM to reload
        // and after that we're refreshing the searchField's locator
        WaitForElement.waitUntilElementIsClickable(searchField, getWebDriverWait());
        searchField.clear();
    }
}
