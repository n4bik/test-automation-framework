package pl.tomaszbuga.pom;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.log4j.Log4j2;
import pl.tomaszbuga.pom.utils.PageWithSubtitle;
import pl.tomaszbuga.tests.models.article.Article;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pl.tomaszbuga.utils.StringUtils.parseListIntoString;
import static pl.tomaszbuga.utils.UserAuth.addAuthCookiesToDriver;

@Log4j2
public class ArticlesPage extends PageWithSubtitle {
    private final String
            articlesUrl = baseUrl + "articles?categoryId=19",
            badgeTagsLocator = ".badge-tag",
            badgeTitleLocator = ".badge-title",
            articleTitleLocator = ".article-title-content",
            articleAuthorFullNameLocator = ".article-author-content",
            articlePublishDateLocator = ".article-create-date-content";

    private final SelenideElement
            categoryBadge = $(".yellow-badge"),
            categoryBadgeTitle = $(".badge-title"),
            goToArticleButton = $(".go-to-article-button"),
            goToArticleSidebar = $(".go-to-article-text");

    private final ElementsCollection
            articles = $$(".article-row");

    public ArticlesPage openArticlesPage() {
        log.info("Open Articles page");
        open(articlesUrl);
        return this;
    }

    public ArticlesPage openArticlesPageWithApiAuth() {
        addAuthCookiesToDriver();
        open(articlesUrl);
        return this;
    }

    public ArticlesPage checkIfArticlesPageLoaded() {
        log.info("Waiting for Articles page to load");
        subtitle.shouldHave(exactText("Please select article"));
        log.info("Articles page loaded");
        return this;
    }

    public String getCategoryIdFromUrl() {
        log.info("Getting Category ID from URL");
        return WebDriverRunner.url().split("=")[1];
    }

    public List<Article> getArticleListFromPage() {
        log.info("Getting Articles list from Articles page");
        List<Article> articlesListFromPage = new ArrayList<>();

        articles
                .shouldBe(sizeGreaterThan(0))
                .forEach(row -> {
                    List<String> categoryTitlesList = new ArrayList<>();
                    List<String> categoryTagsList = new ArrayList<>();

                    row.$$(badgeTagsLocator).forEach(badge -> {
                        categoryTagsList.add(badge.getText());
                        categoryTitlesList.add(
                                badge
                                        .hover()
                                        .find(badgeTitleLocator)
                                        .shouldBe(visible)
                                        .getText());
                    });

                    Article articleFromList = Article.builder()
                            .title(row.find(articleTitleLocator).getText())
                            .authorFullName(row.find(articleAuthorFullNameLocator).getText())
                            .publishDate(row.find(articlePublishDateLocator).getText())
                            .categoryTagList(parseListIntoString(categoryTagsList))
                            .categoryTitleList(parseListIntoString(categoryTitlesList))
                            .build();

                    articlesListFromPage.add(articleFromList);
                });

        return articlesListFromPage;
    }

    public ArticlesPage hoverOverCategoryBadge() {
        log.info("Hovering over category badge");
        categoryBadge.hover();
        return this;
    }

    public ArticlesPage hoverOverGoToArticleButton() {
        log.info("Hovering over Go To Article button");
        goToArticleButton.hover();
        return this;
    }

    public void checkIfCategoryBadgeTitleDisplayed() {
        log.info("Checking if category badge title is displayed");
        categoryBadgeTitle.shouldBe(visible);
    }

    public void checkIfGoToArticleSidebarDisplayed() {
        log.info("Checking if Go To Article sidebar is displayed");
        goToArticleSidebar.shouldBe(visible);
    }

    public ArticleDetailsPage clickGoToArticleButton() {
        log.info("Clicking on Go To Article button");
        goToArticleButton.click();
        return new ArticleDetailsPage();
    }

}
