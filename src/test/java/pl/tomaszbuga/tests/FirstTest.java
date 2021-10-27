package pl.tomaszbuga.tests;

import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test()
    public void firstMethod() {
        LOGGER.info("info message");
        LOGGER.warn("warn message");
        LOGGER.error("error message");
    }
}
