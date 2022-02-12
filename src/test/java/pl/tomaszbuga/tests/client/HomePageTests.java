package pl.tomaszbuga.tests.client;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Epic;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.ArticleDetailsPage;
import pl.tomaszbuga.pom.ArticlesPage;
import pl.tomaszbuga.pom.CategoriesPage;
import pl.tomaszbuga.pom.HomePage;
import pl.tomaszbuga.tests.utils.BaseTest;

@Epic("USD - Client Side")
public class HomePageTests extends BaseTest {

    @Test(description = "Verify that yellow button redirects to Categories Page")
    @TmsLink("USD-1")
    public void verifyThatYellowButtonRedirectsToCategoriesPage() {
        HomePage homePage = new HomePage();
        homePage
                .openHomePage()
                .clickYellowButton()
                .checkIfCategoriesPageLoaded();
    }

    @Test(description = "Verify that User cannot access Categories Page without clicking the yellow button")
    @TmsLink("USD-3")
    public void verifyThatCategoriesPageIsNotAvailableWithoutClickingYellowButton() {
        CategoriesPage categoriesPage = new CategoriesPage();
        categoriesPage.openCategoriesPage();

        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }

    @Test(description = "Verify that User cannot access Articles Page without clicking the yellow button")
    @TmsLink("USD-4")
    public void verifyThatArticlesPageIsNotAvailableWithoutClickingYellowButton() {
        ArticlesPage articlesPage = new ArticlesPage();
        articlesPage.openArticlesPage();

        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }

    @Test(description = "Verify that User cannot access Article Details Page without clicking the yellow button")
    @TmsLink("USD-5")
    public void verifyThatArticleDetailsPageIsNotAvailableWithoutClickingYellowButton() {
        ArticleDetailsPage articleDetailsPage = new ArticleDetailsPage();
        articleDetailsPage.openArticleDetailsPage();

        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }

}
