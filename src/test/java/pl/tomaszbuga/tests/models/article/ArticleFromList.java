package pl.tomaszbuga.tests.models.article;

import java.util.Objects;

public class ArticleFromList {
    public static ArticleFromListBuilder builder() {
        return new ArticleFromListBuilder();
    }

    private final String title;
    private final String authorFullName;
    private final String publishDate;
    private final String categoryTagList;
    private final String categoryTitleList;

    public ArticleFromList(final String title,
                           final String authorFullName,
                           final String publishDate,
                           final String categoryTagList,
                           final String categoryTitleList) {
        this.title = title;
        this.authorFullName = authorFullName;
        this.publishDate = publishDate;
        this.categoryTagList = categoryTagList;
        this.categoryTitleList = categoryTitleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleFromList article = (ArticleFromList) o;

        if (!title.equals(article.title)) return false;
        if (!authorFullName.equals(article.authorFullName)) return false;
        if (!publishDate.equals(article.publishDate)) return false;
        if (!Objects.equals(categoryTagList, article.categoryTagList)) return false;
        return Objects.equals(categoryTitleList, article.categoryTitleList);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + authorFullName.hashCode();
        result = 31 * result + publishDate.hashCode();
        result = 31 * result + (categoryTagList != null ? categoryTagList.hashCode() : 0);
        result = 31 * result + (categoryTitleList != null ? categoryTitleList.hashCode() : 0);
        return result;
    }

}