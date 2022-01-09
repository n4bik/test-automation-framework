package pl.tomaszbuga.tests.client;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.ArticleDetailsPage;
import pl.tomaszbuga.tests.models.article.Article;

import static pl.tomaszbuga.utils.database.DbDataProvider.getArticleDetails;

public class ArticleDetailsPageTests {
    @Test
    public void verifyThatArticleDetailsAreDisplayedCorrectly() {
        ArticleDetailsPage articleDetailsPage = new ArticleDetailsPage();
        openArticleDetailsPageWithApiAuth(articleDetailsPage);
        Article articleDetailsFromDb = getArticleDetails(articleDetailsPage.getArticleIdFromUrl());
        Article articleDetailsFromPage = articleDetailsPage.getArticleDetailsFromPage();

        Assert.assertEquals(articleDetailsFromPage, articleDetailsFromDb);
    }

    private ArticleDetailsPage openArticleDetailsPageWithApiAuth(ArticleDetailsPage articleDetailsPage) {
        return articleDetailsPage
                .openArticleDetailsPageWithApiAuth()
                .checkIfArticleDetailsPageLoaded();
    }
}
