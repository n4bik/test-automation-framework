package pl.tomaszbuga.tests.client;

import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.ArticleDetailsPage;
import pl.tomaszbuga.tests.models.article.Article;
import pl.tomaszbuga.tests.utils.BaseTest;

import static pl.tomaszbuga.utils.database.DbDataProvider.getArticleDetails;

@Epic("USD - Client Side")
public class ArticleDetailsPageTests extends BaseTest {

    @Test(description = "Verify that Article Details are displayed correctly")
    @TmsLink("USD-12")
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
