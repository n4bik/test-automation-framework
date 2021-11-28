package pl.tomaszbuga.tests.models.article;

public class ArticleBuilder {
    private String id;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String summary;
    private String content;
    private String createDate;
    private String publishDate;

    public ArticleBuilder() {

    }

    public ArticleBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public ArticleBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleBuilder setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public ArticleBuilder setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public ArticleBuilder setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public ArticleBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public ArticleBuilder setCreateDate(String createDate) {
        this.createDate = createDate;
        return this;
    }

    public ArticleBuilder setPublishDate(String publishDate) {
        this.publishDate = publishDate;
        // We're assigning empty String instead of null
        // as data from WebPage would never return null
        // thus equals method from Article class would fail on comparison
        if (publishDate == null) {
            this.publishDate = "";
        }
        return this;
    }

    public Article build() {
        return new Article(
                id,
                title,
                authorFirstName,
                authorLastName,
                summary,
                content,
                createDate,
                publishDate);
    }
}
