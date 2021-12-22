package pl.tomaszbuga.tests.client;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.ArticleDetailsPage;
import pl.tomaszbuga.pom.HomePage;
import pl.tomaszbuga.tests.models.article.Article;

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

        Article articleDetailsFromDb = getArticleDetails(articleDetailsPage.getArticleIdFromUrl());

        Article articleDetailsFromPage = articleDetailsPage.getArticleDetailsFromPage();

        Assert.assertEquals(articleDetailsFromPage, articleDetailsFromDb);
    }
}
