package pl.tomaszbuga.tests.client;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pl.tomaszbuga.utils.DbDataProvider.getCategoryTitles;

public class CategoriesPageTests {
    private final Logger LOGGER = LogManager.getLogger(CategoriesPageTests.class);

    @Test
    public void verifyCategoriesListWithDatabase() {
        LOGGER.info("Open Homepage");
        open("http://localhost:4200");

        LOGGER.info("Click yellow button");
        $(".yellow-button").click();

        $(".subtitle-content").shouldHave(exactText("Please select category"));

        List<String> categoryTitlesFromPage =
                $$("app-button")
                        .shouldBe(sizeGreaterThan(0))
                        .texts();
        Assert.assertTrue(CollectionUtils.isEqualCollection(getCategoryTitles(), categoryTitlesFromPage));
    }

    @Test
    public void verifyThatClickOnCategoryRedirectsToArticlesPage() {
        LOGGER.info("Open Homepage");
        open("http://localhost:4200");

        LOGGER.info("Click yellow button");
        $(".yellow-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select category"));

        LOGGER.info("Click Category button");
        $("app-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select article"));
    }
}
