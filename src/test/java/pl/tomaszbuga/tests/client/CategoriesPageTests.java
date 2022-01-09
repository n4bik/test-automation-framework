package pl.tomaszbuga.tests.client;

import org.apache.commons.collections4.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.CategoriesPage;

import java.util.List;

import static pl.tomaszbuga.utils.database.DbDataProvider.getCategoryTitles;

public class CategoriesPageTests {
    private CategoriesPage categoriesPage;

    @BeforeMethod
    public void setup() {
        categoriesPage = new CategoriesPage();
    }

    @Test
    public void verifyCategoriesListWithDatabase() {
        List<String> categoryTitlesFromPage =
                openCategoriesPageWithApiAuth(categoriesPage).getCategoryTitlesFromPage();

        Assert.assertTrue(
                CollectionUtils.isEqualCollection(getCategoryTitles(), categoryTitlesFromPage));
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
