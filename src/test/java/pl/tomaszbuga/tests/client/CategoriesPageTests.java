package pl.tomaszbuga.tests.client;

import org.apache.commons.collections4.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.utils.DbDataProvider;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CategoriesPageTests {
    @Test
    public void verifyThatYellowButtonRedirectsToCategories() {
        List<String> categoryTitles = DbDataProvider.getCategoryTitles();

        open("http://localhost:4200");
        $(".yellow-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select category"));
        List<String> categoryTitlesFromPage =
                $$("app-button")
                        .shouldBe(sizeGreaterThan(0))
                        .texts();
        Assert.assertTrue(CollectionUtils.isEqualCollection(categoryTitles, categoryTitlesFromPage));
    }

    @Test
    public void verifyThatClickOnCategoryRedirectsToArticlesPage() {
        open("http://localhost:4200");
        $(".yellow-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select category"));
        $("app-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select article"));
    }
}
