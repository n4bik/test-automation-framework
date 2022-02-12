package pl.tomaszbuga.tests.client;

import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.apache.commons.collections4.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.CategoriesPage;
import pl.tomaszbuga.tests.utils.BaseTest;

import static pl.tomaszbuga.utils.database.DbDataProvider.getCategoryTitles;

@Epic("USD - Client Side")
public class CategoriesPageTests extends BaseTest {

    private CategoriesPage categoriesPage;

    @BeforeMethod
    public void setup() {
        categoriesPage = new CategoriesPage();
    }

    @Test(description = "Verify that Categories list is displayed correctly (compared with Database)")
    @TmsLink("USD-6")
    public void verifyCategoriesListWithDatabase() {
        Assert.assertTrue(
                CollectionUtils.isEqualCollection(
                        getCategoryTitles(),
                        openCategoriesPageWithApiAuth(categoriesPage).getCategoryTitlesFromPage()
                ));
    }

    @Test(description = "Verify that Category button redirects to Articles Page")
    @TmsLink("USD-7")
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
