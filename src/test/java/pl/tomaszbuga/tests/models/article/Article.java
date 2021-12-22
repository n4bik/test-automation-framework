package pl.tomaszbuga.tests.models.article;

import pl.tomaszbuga.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static pl.tomaszbuga.utils.StringUtils.parseListIntoString;

public class Article {
    private String title;
    private String authorFullName;
    private String publishDate;
    private String summary;
    private String content;
    private String categoryTagList;
    private String categoryTitleList;

    public Article() {
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public Article setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
        return this;
    }

    public Article setPublishDate(String publishDate) {
        this.publishDate = Objects.requireNonNullElse(publishDate, "");
        return this;
    }

    public Article setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public Article setCategoryTagList(String categoryTagList) {
        List<String> sortedTagList =
                Arrays.stream(categoryTagList.split(", "))
                        .sorted()
                        .collect(Collectors.toList());
        this.categoryTagList = parseListIntoString(sortedTagList);
        return this;
    }

    public Article setCategoryTitleList(String categoryTitleList) {
        List<String> sortedTitleList =
                Arrays.stream(categoryTitleList.split(", "))
                        .sorted()
                        .collect(Collectors.toList());
        this.categoryTitleList = parseListIntoString(sortedTitleList);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!title.equals(article.title)) return false;
        if (!authorFullName.equals(article.authorFullName)) return false;
        if (!publishDate.equals(article.publishDate)) return false;
        if (!Objects.equals(summary, article.summary)) return false;
        if (!Objects.equals(content, article.content)) return false;
        if (!Objects.equals(categoryTagList, article.categoryTagList)) return false;
        return Objects.equals(categoryTitleList, article.categoryTitleList);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + authorFullName.hashCode();
        result = 31 * result + publishDate.hashCode();
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (categoryTagList != null ? categoryTagList.hashCode() : 0);
        result = 31 * result + (categoryTitleList != null ? categoryTitleList.hashCode() : 0);
        return result;
    }

}