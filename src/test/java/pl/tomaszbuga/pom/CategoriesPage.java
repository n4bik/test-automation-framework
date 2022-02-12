package pl.tomaszbuga.pom;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pl.tomaszbuga.pom.utils.PageWithSubtitle;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static pl.tomaszbuga.utils.UserAuth.addAuthCookiesToDriver;

@Log4j2
public class CategoriesPage extends PageWithSubtitle {

    private final String categoriesUrl = baseUrl + "categories";

    private final ElementsCollection titlesFromPage = $$("app-button");

    @Step("Open Categories Page")
    public CategoriesPage openCategoriesPage() {
        log.info("Open Categories page");
        open(categoriesUrl);
        return this;
    }

    @Step("Open Categories Page (using the Auth API)")
    public CategoriesPage openCategoriesPageWithApiAuth() {
        addAuthCookiesToDriver();
        open(categoriesUrl);
        return this;
    }

    @Step("Verify that Categories Page is loaded")
    public CategoriesPage checkIfCategoriesPageLoaded() {
        log.info("Waiting for Categories page to load");
        subtitle.shouldHave(exactText("Please select category"));
        log.info("Categories page loaded");
        return this;
    }

    @Step("Get category titles from the Categories Page")
    @Attachment
    public List<String> getCategoryTitlesFromPage() {
        log.info("Getting list of Category titles from Categories page");
        return titlesFromPage
                        .shouldBe(sizeGreaterThan(0))
                        .texts();
    }

    @Step("Click on the first available category on the Categories Page")
    public ArticlesPage clickFirstAvailableCategory() {
        log.info("Click on the first available Category");
        titlesFromPage.get(0).click();
        return new ArticlesPage();
    }

}
