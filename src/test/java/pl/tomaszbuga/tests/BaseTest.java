package pl.tomaszbuga.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v95.network.Network;
import org.openqa.selenium.devtools.v95.network.model.ConnectionType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import java.util.Optional;

public abstract class BaseTest {
    private ChromeDriver driver;
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

    public ChromeDriver getDriver() {
        return driver;
    }

    private void setupChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
    }

    private void configureDriver() {
        DevTools devTools = getDriver().getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                false,
                1,
                20000,
                2000,
                Optional.of(ConnectionType.CELLULAR3G)
        ));
        getDriver().manage().timeouts().implicitlyWait(TIMEOUT);
        getDriver().manage().window().maximize();
    }
}
