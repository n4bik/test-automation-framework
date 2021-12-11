package pl.tomaszbuga.tests.client;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.collections4.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.tests.models.article.Article;
import pl.tomaszbuga.tests.models.article.ArticleBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static pl.tomaszbuga.utils.DbDataProvider.getArticleDetails;
import static pl.tomaszbuga.utils.DbDataProvider.getArticlesListByCategoryId;

public class ArticleDetailsPageTests {
    @Test
    public void verifyThatGoToArticleButtonRedirectsToArticleDetails() {
        open("http://localhost:4200");
        $(".yellow-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select category"));
        $("app-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select article"));
        $(".go-to-article-button").hover();
        $(".go-to-article-text").click();

        String articleIdFromUrl = WebDriverRunner.url().split("=")[1];
        Article articleDetails = getArticleDetails(articleIdFromUrl);

        Article articleDetailsFromPage = new Article();
        articleDetailsFromPage.setTitle($("h1").getText());
        articleDetailsFromPage.setPublishDate($(".article-display-date").getText());
        articleDetailsFromPage.setAuthorFullName($(".article-display-author-name").getText());
        articleDetailsFromPage.setSummary($(".article-display-summary").getText());
        articleDetailsFromPage.setContent($(".article-display-content").innerHtml());

        Assert.assertEquals(articleDetails, articleDetailsFromPage);
    }
}
