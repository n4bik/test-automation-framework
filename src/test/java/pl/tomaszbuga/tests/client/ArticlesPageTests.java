package pl.tomaszbuga.tests.client;

import org.apache.commons.collections4.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.ArticlesPage;
import pl.tomaszbuga.tests.models.article.Article;
import pl.tomaszbuga.utils.DbDataProvider;

import java.util.List;

public class ArticlesPageTests {
    ArticlesPage articlesPage;

    @BeforeMethod
    public void setup() {
        articlesPage = new ArticlesPage();
    }

    @Test
    public void verifyArticlesListWithDataBase() {
        openArticlesPageWithApiAuth(articlesPage);

        List<Article> articlesListFromDb =
                DbDataProvider.getArticlesListByCategoryId(articlesPage.getCategoryIdFromUrl());

        List<Article> articlesListFromPage =
                articlesPage.getArticleListFromPage();

        Assert.assertTrue(CollectionUtils
                .isEqualCollection(articlesListFromDb, articlesListFromPage));
    }

    @Test
    public void verifyTooltipDisplayOnBadgeHover() {
        openArticlesPageWithApiAuth(articlesPage)
                .hoverOverCategoryBadge()
                .checkIfCategoryBadgeTitleDisplayed();
    }

    @Test
    public void verifyOpenArticleSidebarExpandOnHover() {
        openArticlesPageWithApiAuth(articlesPage)
                .hoverOverGoToArticleButton()
                .checkIfGoToArticleSidebarDisplayed();
    }

    @Test
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