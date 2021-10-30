package pl.tomaszbuga.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForElement {
    public static void waitUntilElementIsVisible(WebElement element, WebDriverWait wait) {
        By elementByLocator = new ByLocatorFinder().getByFromWebElement(element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementByLocator));
    }

    public static void waitUntilElementIsClickable(WebElement element, WebDriverWait wait) {
        By elementByLocator = new ByLocatorFinder().getByFromWebElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(elementByLocator));
    }
}
