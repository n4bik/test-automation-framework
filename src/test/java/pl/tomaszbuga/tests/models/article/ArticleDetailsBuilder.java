package pl.tomaszbuga.tests.models.article;

import java.util.Objects;

public class ArticleDetailsBuilder {
    private String title;
    private String authorFullName;
    private String publishDate;
    private String summary;
    private String content;

    ArticleDetailsBuilder() {
    }

    public ArticleDetailsBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleDetailsBuilder setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
        return this;
    }

    public ArticleDetailsBuilder setPublishDate(String publishDate) {
        this.publishDate = Objects.requireNonNullElse(publishDate, "");
        return this;
    }

    public ArticleDetailsBuilder setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public ArticleDetailsBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public ArticleDetails build() {
        return new ArticleDetails(title, authorFullName, publishDate, summary, content);
    }
}
