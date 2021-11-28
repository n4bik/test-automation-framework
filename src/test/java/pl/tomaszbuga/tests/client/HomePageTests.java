package pl.tomaszbuga.tests.client;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class HomePageTests {
    @Test
    public void verifyThatYellowButtonRedirectsToCategoriesPage() {
        open("http://localhost:4200");
        $(".yellow-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select category"));
    }

    @Test
    public void verifyThatCategoriesPageIsNotAvailableWithoutClickingYellowButton() {
        open("http://localhost:4200/categories");
        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }

    @Test
    public void verifyThatArticlesPageIsNotAvailableWithoutClickingYellowButton() {
        open("http://localhost:4200/articles?categoryId=19");
        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }

    @Test
    public void verifyThatArticleDetailsPageIsNotAvailableWithoutClickingYellowButton() {
        open("http://localhost:4200/article?articleId=27");
        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }
}
