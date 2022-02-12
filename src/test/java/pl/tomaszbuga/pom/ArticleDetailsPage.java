package pl.tomaszbuga.pom;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pl.tomaszbuga.pom.utils.BasePage;
import pl.tomaszbuga.tests.models.article.Article;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static pl.tomaszbuga.utils.UserAuth.addAuthCookiesToDriver;

@Log4j2
public class ArticleDetailsPage extends BasePage {

    private final String articleDetailsUrl = baseUrl + "article?articleId=27";

    private final SelenideElement
            articleTitle = $("h1"),
            articlePublishDate = $(".article-display-date"),
            articleAuthorFullName = $(".article-display-author-name"),
            articleSummary = $(".article-display-summary"),
            articleContent = $(".article-display-content");

    @Step("Open Article Details Page")
    public ArticleDetailsPage openArticleDetailsPage() {
        log.info("Open Article Details page");
        open(articleDetailsUrl);
        return this;
    }

    @Step("Open Article Details Page (using the Auth API)")
    public ArticleDetailsPage openArticleDetailsPageWithApiAuth() {
        log.info("Open Article Details page");
        addAuthCookiesToDriver();
        open(articleDetailsUrl);
        return this;
    }

    @Step("Verify that Article Details Page is loaded")
    public ArticleDetailsPage checkIfArticleDetailsPageLoaded() {
        log.info("Waiting for Article Details page to load");
        articleTitle.shouldBe(visible);
        articlePublishDate.shouldBe(visible);
        articleAuthorFullName.shouldBe(visible);
        articleSummary.shouldBe(visible);
        articleContent.shouldBe(visible);
        log.info("Article Details page loaded");
        return this;
    }

    @Step("Get the Article ID from URL")
    public String getArticleIdFromUrl() {
        return WebDriverRunner.url().split("=")[1];
    }

    @Step("Get article details from the Article Details Page")
    public Article getArticleDetailsFromPage() {
        return Article.builder()
                .title(getArticleTitle())
                .publishDate(getArticlePublishDate())
                .authorFullName(getArticleAuthorFullName())
                .summary(getArticleSummary())
                .content(getArticleContent())
                .build();
    }

    @Step("Get article title")
    public String getArticleTitle() {
        return articleTitle.getText();
    }

    @Step("Get article publish date")
    public String getArticlePublishDate() {
        return articlePublishDate.getText();
    }

    @Step("Get article author full name")
    public String getArticleAuthorFullName() {
        return articleAuthorFullName.getText();
    }

    @Step("Get article summary")
    public String getArticleSummary() {
        return articleSummary.getText();
    }

    @Step("Get article content")
    public String getArticleContent() {
        return articleContent.innerHtml();
    }

}
