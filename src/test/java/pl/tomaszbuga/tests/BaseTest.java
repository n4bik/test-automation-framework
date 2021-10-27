package pl.tomaszbuga.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public abstract class BaseTest {
    private WebDriver driver;
    private final Duration TIMEOUT = Duration.ofSeconds(10);
    protected static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setupAndConfigureDriver() {
        setupChromeDriver();
        configureDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        getDriver().quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private void configureDriver() {
        getDriver().manage().timeouts().implicitlyWait(TIMEOUT);
        getDriver().manage().window().maximize();
    }
}
