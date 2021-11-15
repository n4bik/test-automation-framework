package pl.tomaszbuga.tests;

import org.testng.annotations.Test;
import pl.tomaszbuga.pom.HomePage;

public class FirstTest extends BaseTest {
    @Test()
    public void firstMethod() {
        HomePage homePage = new HomePage(getDriver());

        long startTime = System.currentTimeMillis();
        homePage.getTomaszbugaHomepage();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }
}
