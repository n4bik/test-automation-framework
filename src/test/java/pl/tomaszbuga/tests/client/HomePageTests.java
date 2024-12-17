package pl.tomaszbuga.tests.client;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class HomePageTests {
    private final Logger LOGGER = LogManager.getLogger(HomePageTests.class);

    @Test
    public void verifyThatYellowButtonRedirectsToCategoriesPage() {
        LOGGER.info("Open Homepage");
        open("http://localhost:4200");

        LOGGER.info("Click yellow button");
        $(".yellow-button").click();

        $(".subtitle-content").shouldHave(exactText("Please select category"));
    }

    @Test
    public void verifyThatCategoriesPageIsNotAvailableWithoutClickingYellowButton() {
        LOGGER.info("Open Categories page");
        open("http://localhost:4200/categories");

        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }

    @Test
    public void verifyThatArticlesPageIsNotAvailableWithoutClickingYellowButton() {
        LOGGER.info("Open Articles page");
        open("http://localhost:4200/articles?categoryId=19");

        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }

    @Test
    public void verifyThatArticleDetailsPageIsNotAvailableWithoutClickingYellowButton() {
        LOGGER.info("Open Article details page");
        open("http://localhost:4200/article?articleId=27");

        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }
}
