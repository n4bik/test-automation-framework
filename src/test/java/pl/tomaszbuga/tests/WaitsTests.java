package pl.tomaszbuga.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v95.network.Network;
import org.openqa.selenium.devtools.v95.network.model.ConnectionType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class WaitsTests {
    private final Duration
            EXPLICIT_TIMEOUT = Duration.ofSeconds(30),
            IMPLICIT_TIMEOUT = Duration.ofSeconds(30);
    private final String
            LIST_OF_ALL_ARTICLES_BUTTON = "List of all Articles",
            GO_TO_ARTICLE_ICON = "go-to-article-icon",
            AVAILABLE_CATEGORIES_NEW_METHODS = "//div[contains(text(),'Available Categories')]",
            CATEGORY_ITEM_NEW_METHODS = "architect-edit-link",
            FIRST_AVAILABLE_CATEGORY_LOCATOR = "//div[contains(text(),'Available Categories')]/following-sibling::div/p",
            SELECTED_CATEGORIES_LOCATOR = "//div[contains(text(),'Available Categories')]/preceding-sibling::div/p";

    private ChromeDriver driver;

    @BeforeMethod
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // configureDriver();
        // Network Throttling/Slow Network Connection messes up the .below()/.above() methods
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void withoutWait() {
        login();

        // This method will fail because it requires
        // some time before the button is available after logging in
        driver.findElement(By.linkText(LIST_OF_ALL_ARTICLES_BUTTON)).click();
    }

    @Test
    public void explicitWait() {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        long startTime = System.currentTimeMillis();

        login();

        By listOfAllArticlesButton = By.linkText(LIST_OF_ALL_ARTICLES_BUTTON);
        wait.until(ExpectedConditions.elementToBeClickable(listOfAllArticlesButton));
        driver.findElement(listOfAllArticlesButton).click();

        By goToArticleIcon = By.className(GO_TO_ARTICLE_ICON);
        wait.until(ExpectedConditions.elementToBeClickable(goToArticleIcon));
        driver.findElement(goToArticleIcon).click();

        By firstAvailableCategoryLocator = By.xpath(FIRST_AVAILABLE_CATEGORY_LOCATOR);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstAvailableCategoryLocator));
        WebElement firstAvailableCategory = driver.findElement(firstAvailableCategoryLocator);
        String categoryTitle = firstAvailableCategory.getText();
        firstAvailableCategory.click();

        By selectedCategoriesLocator = By.xpath(SELECTED_CATEGORIES_LOCATOR);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectedCategoriesLocator));
        List<WebElement> selectedCategories = driver.findElements(selectedCategoriesLocator);
        String lastSelectedCategory = selectedCategories.get(selectedCategories.size() - 1).getText();

        Assert.assertEquals(
                removePlusOrMinusSign(lastSelectedCategory),
                removePlusOrMinusSign(categoryTitle));

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }

    @Test
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT);
        long startTime = System.currentTimeMillis();

        login();

        By listOfAllArticlesButton = By.linkText(LIST_OF_ALL_ARTICLES_BUTTON);
        driver.findElement(listOfAllArticlesButton).click();

        By goToArticleIcon = By.className(GO_TO_ARTICLE_ICON);
        driver.findElement(goToArticleIcon).click();

        By firstAvailableCategoryLocator = By.xpath(FIRST_AVAILABLE_CATEGORY_LOCATOR);
        WebElement firstAvailableCategory = driver.findElement(firstAvailableCategoryLocator);
        String categoryTitle = firstAvailableCategory.getText();
        firstAvailableCategory.click();

        By selectedCategoriesLocator = By.xpath(SELECTED_CATEGORIES_LOCATOR);
        List<WebElement> selectedCategories = driver.findElements(selectedCategoriesLocator);
        String lastSelectedCategory = selectedCategories.get(selectedCategories.size() - 1).getText();

        Assert.assertEquals(
                removePlusOrMinusSign(lastSelectedCategory),
                removePlusOrMinusSign(categoryTitle));

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }

    @Test
    public void explicitAndImplicit() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        long startTime = System.currentTimeMillis();

        login();

        By listOfAllArticlesButton = By.linkText(LIST_OF_ALL_ARTICLES_BUTTON);
        wait.until(ExpectedConditions.elementToBeClickable(listOfAllArticlesButton));
        driver.findElement(listOfAllArticlesButton).click();

        By goToArticleIcon = By.className(GO_TO_ARTICLE_ICON);
        wait.until(ExpectedConditions.elementToBeClickable(goToArticleIcon));
        driver.findElement(goToArticleIcon).click();

        By firstAvailableCategoryLocator = By.xpath(FIRST_AVAILABLE_CATEGORY_LOCATOR);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstAvailableCategoryLocator));
        WebElement firstAvailableCategory = driver.findElement(firstAvailableCategoryLocator);
        String categoryTitle = firstAvailableCategory.getText();
        firstAvailableCategory.click();

        By selectedCategoriesLocator = By.xpath(SELECTED_CATEGORIES_LOCATOR);
        List<WebElement> selectedCategories = driver.findElements(selectedCategoriesLocator);
        String lastSelectedCategory = selectedCategories.get(selectedCategories.size() - 1).getText();

        Assert.assertEquals(
                removePlusOrMinusSign(lastSelectedCategory),
                removePlusOrMinusSign(categoryTitle));

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }

    @Test
    public void explicitWaitWithBelowAndAboveMethods() {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        long startTime = System.currentTimeMillis();

        login();

        By listOfAllArticlesButton = By.linkText(LIST_OF_ALL_ARTICLES_BUTTON);
        wait.until(ExpectedConditions.elementToBeClickable(listOfAllArticlesButton));
        driver.findElement(listOfAllArticlesButton).click();

        By goToArticleIcon = By.className(GO_TO_ARTICLE_ICON);
        wait.until(ExpectedConditions.elementToBeClickable(goToArticleIcon));
        driver.findElement(goToArticleIcon).click();

        // You can't use the brand new .below() & .above() methods with the Explicit wait AND Network Throttling due to:
        // org.openqa.selenium.NoSuchElementException: Cannot locate an element using [unknown locator]

        By availableCategoriesTitle = By.xpath(AVAILABLE_CATEGORIES_NEW_METHODS);
        By categoryItem = By.className(CATEGORY_ITEM_NEW_METHODS);

        wait.until(ExpectedConditions.visibilityOfElementLocated(availableCategoriesTitle));
        WebElement firstAvailableCategory =
                driver.findElement(with(categoryItem).below(availableCategoriesTitle));

        String categoryTitle = firstAvailableCategory.getText();
        firstAvailableCategory.click();
        String lastSelectedCategory =
                driver.findElement(with(categoryItem).above(availableCategoriesTitle)).getText();

        Assert.assertEquals(
                removePlusOrMinusSign(lastSelectedCategory),
                removePlusOrMinusSign(categoryTitle));

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }

    @Test
    public void implicitWaitWithBelowAndAboveMethods() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT);
        long startTime = System.currentTimeMillis();

        login();

        By listOfAllArticlesButton = By.linkText(LIST_OF_ALL_ARTICLES_BUTTON);
        driver.findElement(listOfAllArticlesButton).click();

        By goToArticleIcon = By.className(GO_TO_ARTICLE_ICON);
        driver.findElement(goToArticleIcon).click();

        // You can't use the brand new .below() & .above() methods with the Implicit wait due to:
        // org.openqa.selenium.NoSuchElementException: Cannot locate an element using [unknown locator]

        By availableCategoriesTitle = By.xpath(AVAILABLE_CATEGORIES_NEW_METHODS);
        By categoryItem = By.className(CATEGORY_ITEM_NEW_METHODS);

        WebElement firstAvailableCategory =
                driver.findElement(with(categoryItem).below(availableCategoriesTitle));

        String categoryTitle = firstAvailableCategory.getText();
        firstAvailableCategory.click();
        String lastSelectedCategory =
                driver.findElement(with(categoryItem).above(availableCategoriesTitle)).getText();
        Assert.assertEquals(
                removePlusOrMinusSign(lastSelectedCategory),
                removePlusOrMinusSign(categoryTitle));

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }

    @Test
    public void explicitAndImplicitWithBelowAndAboveMethods() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        long startTime = System.currentTimeMillis();

        login();

        By listOfAllArticlesButton = By.linkText(LIST_OF_ALL_ARTICLES_BUTTON);
        wait.until(ExpectedConditions.elementToBeClickable(listOfAllArticlesButton));
        driver.findElement(listOfAllArticlesButton).click();

        By goToArticleIcon = By.className(GO_TO_ARTICLE_ICON);
        wait.until(ExpectedConditions.elementToBeClickable(goToArticleIcon));
        driver.findElement(goToArticleIcon).click();

        By availableCategoriesTitle = By.xpath(AVAILABLE_CATEGORIES_NEW_METHODS);
        By categoryItem = (By.className(CATEGORY_ITEM_NEW_METHODS));

        wait.until(ExpectedConditions.visibilityOfElementLocated(availableCategoriesTitle));
        WebElement firstAvailableCategory =
                driver.findElement(with(categoryItem).below(availableCategoriesTitle));
        String categoryTitle = firstAvailableCategory.getText();
        firstAvailableCategory.click();
        String lastSelectedCategory =
                driver.findElement(with(categoryItem).above(availableCategoriesTitle)).getText();

        Assert.assertEquals(
                removePlusOrMinusSign(lastSelectedCategory),
                removePlusOrMinusSign(categoryTitle));

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }

    private void login() {
        String
                BASE_URL = "https://ultimatestackdeveloper.netlify.app/the/architect",
                USERNAME_ID = "username",
                USERNAME_VALUE = "tbuga",
                PASSWORD_ID = "password",
                PASSWORD_VALUE = "4dm1n?!",
                YELLOW_BUTTON_CLASSNAME = "yellow-button-reversed";

        driver.get(BASE_URL);
        driver.findElement(By.id(USERNAME_ID)).sendKeys(USERNAME_VALUE);
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(PASSWORD_VALUE);
        driver.findElement(By.className(YELLOW_BUTTON_CLASSNAME)).click();
    }

    private String removePlusOrMinusSign(String categoryTitle) {
        return categoryTitle.substring(2);
    }

    private void configureDriver() {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                false,
                1,
                20000,
                2000,
                Optional.of(ConnectionType.CELLULAR4G)
        ));
        driver.manage().window().maximize();
    }
}
