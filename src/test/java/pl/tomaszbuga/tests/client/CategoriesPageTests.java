package pl.tomaszbuga.tests.client;

import org.apache.commons.collections4.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.CategoriesPage;

import static pl.tomaszbuga.utils.database.DbDataProvider.getCategoryTitles;

public class CategoriesPageTests {
    private CategoriesPage categoriesPage;

    @BeforeMethod
    public void setup() {
        categoriesPage = new CategoriesPage();
    }

    @Test
    public void verifyCategoriesListWithDatabase() {
        Assert.assertTrue(
                CollectionUtils.isEqualCollection(
                        getCategoryTitles(),
                        openCategoriesPageWithApiAuth(categoriesPage).getCategoryTitlesFromPage()
                ));
    }

    @Test
    public void verifyThatClickOnCategoryRedirectsToArticlesPage() {
        openCategoriesPageWithApiAuth(categoriesPage)
                .clickFirstAvailableCategory()
                .checkIfArticlesPageLoaded();
    }

    private CategoriesPage openCategoriesPageWithApiAuth(CategoriesPage categoriesPage) {
        return categoriesPage
                .openCategoriesPageWithApiAuth()
                .checkIfCategoriesPageLoaded();
    }

}
