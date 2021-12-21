package pl.tomaszbuga.tests.client;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.ArticleDetailsPage;
import pl.tomaszbuga.pom.HomePage;
import pl.tomaszbuga.tests.models.article.ArticleDetails;

import static pl.tomaszbuga.utils.DbDataProvider.getArticleDetails;

public class ArticleDetailsPageTests {
    @Test
    public void verifyThatArticleDetailsAreDisplayedCorrectly() {
        HomePage homePage = new HomePage();
        ArticleDetailsPage articleDetailsPage = homePage
                .openHomePage()
                .clickYellowButton()
                .checkIfCategoriesPageLoaded()
                .clickFirstAvailableCategory()
                .checkIfArticlesPageLoaded()
                .hoverOverGoToArticleButton()
                .clickGoToArticleButton()
                .checkIfArticleDetailsPageLoaded();

        ArticleDetails articleDetailsFromDb = getArticleDetails(articleDetailsPage.getArticleIdFromUrl());

        ArticleDetails articleDetailsFromPage = ArticleDetails.builder()
                .setTitle(articleDetailsPage.getArticleTitle())
                .setPublishDate(articleDetailsPage.getArticlePublishDate())
                .setAuthorFullName(articleDetailsPage.getArticleAuthorFullName())
                .setSummary(articleDetailsPage.getArticleSummary())
                .setContent(articleDetailsPage.getArticleContent())
                .build();

        Assert.assertEquals(articleDetailsFromPage, articleDetailsFromDb);
    }
}
