package pl.tomaszbuga.tests.client;

import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import org.apache.commons.collections4.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.ArticlesPage;
import pl.tomaszbuga.tests.models.article.Article;
import pl.tomaszbuga.tests.utils.BaseTest;
import pl.tomaszbuga.utils.database.DbDataProvider;

import java.util.List;

@Epic("USD - Client Side")
public class ArticlesPageTests extends BaseTest {

    ArticlesPage articlesPage;

    @BeforeMethod
    public void setup() {
        articlesPage = new ArticlesPage();
    }

    @Test(description = "Verify that Article List is displayed correctly (compared with Database)")
    @TmsLink("USD-8")
    @Issue("USD-13")
    public void verifyArticlesListWithDataBase() {
        openArticlesPageWithApiAuth(articlesPage);

        List<Article> articlesListFromDb =
                DbDataProvider.getArticlesListByCategoryId(articlesPage.getCategoryIdFromUrl());

        List<Article> articlesListFromPage =
                articlesPage.getArticleListFromPage();

        Assert.assertTrue(CollectionUtils
                .isEqualCollection(articlesListFromDb, articlesListFromPage));
    }

    @Test(description = "Verify that Tooltip displays when User hovers over Category Badge")
    @TmsLink("USD-9")
    public void verifyTooltipDisplayOnBadgeHover() {
        openArticlesPageWithApiAuth(articlesPage)
                .hoverOverCategoryBadge()
                .checkIfCategoryBadgeTitleDisplayed();
    }

    @Test(description = "Verify that Open Article sidebar expands on hovering over Go To Article icon")
    @TmsLink("USD-10")
    public void verifyOpenArticleSidebarExpandOnHover() {
        openArticlesPageWithApiAuth(articlesPage)
                .hoverOverGoToArticleButton()
                .checkIfGoToArticleSidebarDisplayed();
    }

    @Test(description = "Verify that Go To Article button redirects to Articles")
    @TmsLink("USD-11")
    public void verifyThatGoToArticleButtonRedirectsToArticleDetails() {
        openArticlesPageWithApiAuth(articlesPage)
                .clickGoToArticleButton()
                .checkIfArticleDetailsPageLoaded();
    }

    private ArticlesPage openArticlesPageWithApiAuth(ArticlesPage articlesPage) {
        return articlesPage
                .openArticlesPageWithApiAuth()
                .checkIfArticlesPageLoaded();
    }

}