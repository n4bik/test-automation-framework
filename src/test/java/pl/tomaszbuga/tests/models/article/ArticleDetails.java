package pl.tomaszbuga.tests.models.article;

import java.util.Objects;

public class ArticleDetails {

    public static ArticleDetailsBuilder builder() {
        return new ArticleDetailsBuilder();
    }

    private final String title;
    private final String authorFullName;
    private final String publishDate;
    private final String summary;
    private final String content;

    ArticleDetails(final String title,
                   final String authorFullName,
                   final String publishDate,
                   final String summary,
                   final String content) {
        this.title = title;
        this.authorFullName = authorFullName;
        this.publishDate = publishDate;
        this.summary = summary;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleDetails articleDetails = (ArticleDetails) o;

        if (!title.equals(articleDetails.title)) return false;
        if (!authorFullName.equals(articleDetails.authorFullName)) return false;
        if (!publishDate.equals(articleDetails.publishDate)) return false;
        if (!Objects.equals(summary, articleDetails.summary)) return false;
        return Objects.equals(content, articleDetails.content);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + authorFullName.hashCode();
        result = 31 * result + publishDate.hashCode();
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

}