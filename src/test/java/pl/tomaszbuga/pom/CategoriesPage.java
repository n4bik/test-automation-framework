package pl.tomaszbuga.pom;

import com.codeborne.selenide.ElementsCollection;
import pl.tomaszbuga.pom.utils.PageWithSubtitle;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class CategoriesPage extends PageWithSubtitle {
    private final String categoriesUrl = baseUrl + "categories";
    private final ElementsCollection titlesFromPage = $$("app-button");

    public CategoriesPage() {
        super(CategoriesPage.class);
    }

    public CategoriesPage openCategoriesPage() {
        LOGGER.info("Open Categories page");
        open(categoriesUrl);
        return this;
    }

    public CategoriesPage checkIfCategoriesPageLoaded() {
        LOGGER.info("Waiting for Categories page to load");
        subtitle.shouldHave(exactText("Please select category"));
        LOGGER.info("Categories page loaded");
        return this;
    }

    public List<String> getCategoryTitlesFromPage() {
        LOGGER.info("Getting list of Category titles from Categories page");
        return titlesFromPage
                        .shouldBe(sizeGreaterThan(0))
                        .texts();
    }

    public ArticlesPage clickFirstAvailableCategory() {
        titlesFromPage.get(0).click();
        return new ArticlesPage();
    }
}
