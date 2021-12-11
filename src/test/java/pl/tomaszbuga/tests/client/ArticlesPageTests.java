package pl.tomaszbuga.tests.client;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.collections4.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.tests.models.article.Article;

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

                    Article article = new Article();
                    article.setTitle(row.$(".article-title-content").getText());
                    article.setAuthorFullName(row.$(".article-author-content").getText());
                    article.setPublishDate(row.$(".article-create-date-content").getText());
                    article.setCategoryTagList(String.join(", ", categoryTagsList));
                    article.setCategoryTitleList(String.join(", ", categoryTitlesList));

                    articlesListFromPage.add(article);
                });

        Assert.assertTrue(CollectionUtils.isEqualCollection(articlesList, articlesListFromPage));
    }

}