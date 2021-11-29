package pl.tomaszbuga.tests.models.article;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArticleBuilder {
    private String title;
    private String authorFullName;
    private String summary;
    private String content;
    private String createDate;
    private String publishDate;
    private String categoryTagList;
    private String categoryTitleList;

    public ArticleBuilder() {

    }

    public ArticleBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleBuilder setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
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

    public ArticleBuilder setCategoryTagList(String categoryTagList) {
        this.categoryTagList = Arrays
                .stream(categoryTagList.split(", "))
                .sorted()
                .collect(Collectors.toList())
                .toString()
                .replace("[", "")
                .replace("]", "")
                .trim();
        return this;
    }

    public ArticleBuilder setCategoryTitleList(String categoryTitleList) {
        this.categoryTitleList = Arrays
                .stream(categoryTitleList.split(", "))
                .sorted()
                .collect(Collectors.toList())
                .toString()
                .replace("[", "")
                .replace("]", "")
                .trim();
        return this;
    }

    public Article build() {
        return new Article(
                title,
                authorFullName,
                summary,
                content,
                createDate,
                publishDate,
                categoryTagList,
                categoryTitleList);
    }
}
