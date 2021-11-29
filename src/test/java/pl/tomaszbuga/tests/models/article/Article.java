package pl.tomaszbuga.tests.models.article;

import java.util.Objects;

public class Article {
    private String title;
    private String authorFullName;
    private String summary;
    private String content;
    private String createDate;
    private String publishDate;
    private String categoryTagList;
    private String categoryTitleList;

    public Article(String title,
                   String authorFullName,
                   String summary,
                   String content,
                   String createDate,
                   String publishDate,
                   String categoryTagList,
                   String categoryTitleList) {
        this.title = title;
        this.authorFullName = authorFullName;
        this.summary = summary;
        this.content = content;
        this.createDate = createDate;
        this.publishDate = publishDate;
        this.categoryTagList = categoryTagList;
        this.categoryTitleList = categoryTitleList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setCategoryTagList(String categoryTagList) {
        this.categoryTagList = categoryTagList;
    }

    public void setCategoryTitleList(String categoryTitleList) {
        this.categoryTitleList = categoryTitleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!Objects.equals(authorFullName, article.authorFullName)) return false;
        if (!Objects.equals(summary, article.summary)) return false;
        if (!Objects.equals(content, article.content)) return false;
        if (!Objects.equals(createDate, article.createDate)) return false;
        if (!Objects.equals(publishDate, article.publishDate)) return false;
        if (!Objects.equals(categoryTagList, article.categoryTagList)) return false;
        if (!Objects.equals(categoryTitleList, article.categoryTitleList)) return false;
        return title.equals(article.title);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + authorFullName.hashCode();
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + publishDate.hashCode();
        result = 31 * result + categoryTagList.hashCode();
        result = 31 * result + categoryTitleList.hashCode();
        return result;
    }
}
