package pl.tomaszbuga.tests.client;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.tests.models.article.Article;
import pl.tomaszbuga.tests.models.article.ArticleBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pl.tomaszbuga.utils.DbDataProvider.getArticlesListByCategoryId;

public class ArticlesPageTests {
    @Test
    public void verifyArticlesListWithDataBase() {
        open("http://localhost:4200");
        $(".yellow-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select category"));
        $("app-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select article"));
        String categoryIdFromUrl = WebDriverRunner.url().split("=")[1];
        List<Article> articlesList = getArticlesListByCategoryId(categoryIdFromUrl);
        List<Article> articlesListFromPage = new ArrayList<>();

        $$(".article-row")
                .shouldBe(sizeGreaterThan(0))
                .forEach(row -> {
                    List<String> categoryTitlesList = new ArrayList<>();
                    List<String> categoryTagsList = new ArrayList<>();

                    row.$$(".badge-tag").forEach(badge -> {
                        categoryTagsList.add(badge.getText());
                        categoryTitlesList.add(badge.hover().$(".badge-title").shouldBe(visible).getText());
                    });

                    Article article = new ArticleBuilder()
                            .setTitle(row.find(".article-title-content").getText())
                            .setAuthorFullName(row.find(".article-author-content").getText())
                            // PublishDate is incorrectly named on the webpage as create-date
                            .setPublishDate(row.find(".article-create-date-content").getText())
                            .setCategoryTagList(categoryTagsList
                                    .toString()
                                    .replace("[", "")
                                    .replace("]", ""))
                            .setCategoryTitleList(categoryTitlesList
                                    .toString()
                                    .replace("[", "")
                                    .replace("]", ""))
                            .build();

                    articlesListFromPage.add(article);
                });

        Assert.assertTrue(CollectionUtils.isEqualCollection(articlesList, articlesListFromPage));
    }

    @Test
    public void verifyTooltipDisplayOnBadgeHover() {
        open("http://localhost:4200");
        $(".yellow-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select category"));
        $("app-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select article"));
        $(".yellow-badge").hover();
        Assert.assertTrue($(".badge-title").isDisplayed());
    }

    @Test
    public void verifyOpenArticleSidebarExpandOnHover() {
        open("http://localhost:4200");
        $(".yellow-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select category"));
        $("app-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select article"));
        $(".go-to-article-button").hover();
        Assert.assertTrue($(".go-to-article-text").isDisplayed());
    }

    @Test
    public void verifyThatGoToArticleButtonRedirectsToArticleDetails() {
        open("http://localhost:4200");
        $(".yellow-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select category"));
        $("app-button").click();
        $(".subtitle-content").shouldHave(exactText("Please select article"));
        $(".go-to-article-button").hover();
        $(".go-to-article-text").click();
        Assert.assertTrue(WebDriverRunner.url().contains("articleId"));
    }
}
