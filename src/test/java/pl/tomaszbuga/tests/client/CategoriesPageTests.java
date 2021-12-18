package pl.tomaszbuga.tests.client;

import org.apache.commons.collections4.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.HomePage;

import java.util.List;

import static pl.tomaszbuga.utils.DbDataProvider.getCategoryTitles;

public class CategoriesPageTests {

    @Test
    public void verifyCategoriesListWithDatabase() {
        HomePage homePage = new HomePage();
        List<String> categoryTitlesFromPage = homePage
                .openHomePage()
                .clickYellowButton()
                .checkIfCategoriesPageLoaded()
                .getCategoryTitlesFromPage();

        Assert.assertTrue(CollectionUtils.isEqualCollection(getCategoryTitles(), categoryTitlesFromPage));
    }

    @Test
    public void verifyThatClickOnCategoryRedirectsToArticlesPage() {
        HomePage homePage = new HomePage();
        homePage
                .openHomePage()
                .clickYellowButton()
                .checkIfCategoriesPageLoaded()
                .clickFirstAvailableCategory()
                .checkIfArticlesPageLoaded();
    }
}
