package pl.tomaszbuga.pom;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
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

    public ArticleDetailsPage openArticleDetailsPage() {
        log.info("Open Article Details page");
        open(articleDetailsUrl);
        return this;
    }

    public ArticleDetailsPage openArticleDetailsPageWithApiAuth() {
        log.info("Open Article Details page");
        addAuthCookiesToDriver();
        open(articleDetailsUrl);
        return this;
    }

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

    public String getArticleIdFromUrl() {
        return WebDriverRunner.url().split("=")[1];
    }

    public Article getArticleDetailsFromPage() {
        return Article.builder()
                .title(getArticleTitle())
                .publishDate(getArticlePublishDate())
                .authorFullName(getArticleAuthorFullName())
                .summary(getArticleSummary())
                .content(getArticleContent())
                .build();
    }

    public String getArticleTitle() {
        return articleTitle.getText();
    }

    public String getArticlePublishDate() {
        return articlePublishDate.getText();
    }

    public String getArticleAuthorFullName() {
        return articleAuthorFullName.getText();
    }

    public String getArticleSummary() {
        return articleSummary.getText();
    }

    public String getArticleContent() {
        return articleContent.innerHtml();
    }
}
