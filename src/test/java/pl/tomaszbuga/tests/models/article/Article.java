package pl.tomaszbuga.tests.models.article;

public class Article {
    private String id;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String summary;
    private String content;
    private String createDate;
    private String publishDate;

    public Article(String id,
                   String title,
                   String authorFirstName,
                   String authorLastName,
                   String summary,
                   String content,
                   String createDate,
                   String publishDate) {
        this.id = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.summary = summary;
        this.content = content;
        this.createDate = createDate;
        this.publishDate = publishDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!authorFirstName.equals(article.authorFirstName)) return false;
        if (!authorLastName.equals(article.authorLastName)) return false;
        if (!publishDate.equals(article.publishDate)) return false;
        return title.equals(article.title);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + authorFirstName.hashCode();
        result = 31 * result + authorLastName.hashCode();
        return result;
    }
}
