package pl.tomaszbuga.tests.utils;

import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static pl.tomaszbuga.utils.AllureEnvironmentWriter.createEnvironmentPropertiesFile;

public class BaseTest {

    @BeforeSuite
    public void setUpEnvironment() {
        open();
        createEnvironmentPropertiesFile();
        closeWebDriver();
    }

}
