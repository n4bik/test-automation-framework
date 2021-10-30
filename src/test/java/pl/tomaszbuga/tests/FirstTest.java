package pl.tomaszbuga.tests;

import org.testng.annotations.Test;
import pl.tomaszbuga.pom.HomePage;

public class FirstTest extends BaseTest {

    @Test()
    public void firstMethod() {
        HomePage homePage = new HomePage(getDriver());
        homePage.seleniumStaleElementTest();
    }
}
