package pl.tomaszbuga.utils;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

import java.lang.reflect.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pl.tomaszbuga.utils.ByLocatorFromString.getByLocatorFromString;

public class ByLocatorFinder {
    private static final String BY = "by";
    private static final String H = "h";
    private static final String LOCATOR = "locator";
    private static final String FOUND_BY = "foundBy";

    public By getByFromWebElement(WebElement element) {
        try {
            if (element instanceof DefaultElementLocator) {
                return getByLocator(element);

            } else if (element instanceof Proxy) {
                Object proxyOrigin = getField(element, H);
                Object locator = getField(proxyOrigin, LOCATOR);

                return getByLocator(locator);

            } else /* if WebElement is RemoteWebElement */ {
                String foundByString = getFoundBy(element);
                String foundByPattern = "(?<=\\-> ).*";

                Pattern pattern = Pattern.compile(foundByPattern);
                Matcher matcher = pattern.matcher(foundByString);

                if (matcher.find()) {
                    int locatorDefinitionIndex = 0;
                    String locatorDefinition = matcher.group(locatorDefinitionIndex);

                    return getByLocatorFromString(locatorDefinition);

                } else {
                    throw new IllegalStateException("Failed to get locator from RemoteWebElement. Please, check if the Regex pattern is valid.");
                }

            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to get locator from WebElement, due to: ", e);
        }
    }

    private Object getField(Object element, String fieldName) throws IllegalAccessException {
        return FieldUtils.readField(element, fieldName, true);
    }

    private String getFoundBy(Object element) throws IllegalAccessException {
        return (String) FieldUtils.readField(element, FOUND_BY, true);
    }

    private By getByLocator(Object element) throws IllegalAccessException {
        return (By) FieldUtils.readField(element, BY, true);
    }

}