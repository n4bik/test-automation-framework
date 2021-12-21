package pl.tomaszbuga.tests.client;

import org.apache.commons.collections4.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.ArticlesPage;
import pl.tomaszbuga.pom.HomePage;
import pl.tomaszbuga.tests.models.article.ArticleFromList;
import pl.tomaszbuga.utils.DbDataProvider;

import java.util.List;

public class ArticlesPageTests {
    @Test
    public void verifyArticlesListWithDataBase() {
        HomePage homePage = new HomePage();
        ArticlesPage articlesPage = homePage
                .openHomePage()
                .clickYellowButton()
                .checkIfCategoriesPageLoaded()
                .clickFirstAvailableCategory()
                .checkIfArticlesPageLoaded();

        List<ArticleFromList> articlesListFromDb =
                DbDataProvider.getArticlesListByCategoryId(articlesPage.getCategoryIdFromUrl());

        Assert.assertTrue(CollectionUtils
                .isEqualCollection(articlesListFromDb, articlesPage.getArticleListFromPage()));
    }

    @Test
    public void verifyTooltipDisplayOnBadgeHover() {
        HomePage homePage = new HomePage();
        homePage
                .openHomePage()
                .clickYellowButton()
                .checkIfCategoriesPageLoaded()
                .clickFirstAvailableCategory()
                .checkIfArticlesPageLoaded()
                .hoverOverCategoryBadge()
                .checkIfCategoryBadgeTitleDisplayed();
    }

    @Test
    public void verifyOpenArticleSidebarExpandOnHover() {
        HomePage homePage = new HomePage();
        homePage
                .openHomePage()
                .clickYellowButton()
                .checkIfCategoriesPageLoaded()
                .clickFirstAvailableCategory()
                .checkIfArticlesPageLoaded()
                .hoverOverGoToArticleButton()
                .checkIfGoToArticleSidebarDisplayed();
    }

    @Test
    public void verifyThatGoToArticleButtonRedirectsToArticleDetails() {
        HomePage homePage = new HomePage();
        homePage
                .openHomePage()
                .clickYellowButton()
                .checkIfCategoriesPageLoaded()
                .clickFirstAvailableCategory()
                .checkIfArticlesPageLoaded()
                .clickGoToArticleButton()
                .checkIfArticleDetailsPageLoaded();
    }

}