package pl.tomaszbuga.tests.client;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.pom.ArticleDetailsPage;
import pl.tomaszbuga.pom.ArticlesPage;
import pl.tomaszbuga.pom.CategoriesPage;
import pl.tomaszbuga.pom.HomePage;

public class HomePageTests {
    @Test
    public void verifyThatYellowButtonRedirectsToCategoriesPage() {
        HomePage homePage = new HomePage();
        homePage
                .openHomePage()
                .clickYellowButton()
                .checkIfCategoriesPageLoaded();
    }

    @Test
    public void verifyThatCategoriesPageIsNotAvailableWithoutClickingYellowButton() {
        CategoriesPage categoriesPage = new CategoriesPage();
        categoriesPage.openCategoriesPage();

        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }

    @Test
    public void verifyThatArticlesPageIsNotAvailableWithoutClickingYellowButton() {
        ArticlesPage articlesPage = new ArticlesPage();
        articlesPage.openArticlesPage();

        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }

    @Test
    public void verifyThatArticleDetailsPageIsNotAvailableWithoutClickingYellowButton() {
        ArticleDetailsPage articleDetailsPage = new ArticleDetailsPage();
        articleDetailsPage.openArticleDetailsPage();

        Assert.assertEquals(WebDriverRunner.url(), "http://localhost:4200/");
    }
}
