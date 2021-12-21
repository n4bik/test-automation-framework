package pl.tomaszbuga.tests.models.article;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static pl.tomaszbuga.utils.StringUtils.parseListIntoString;

public class ArticleFromListBuilder {
    private String title;
    private String authorFullName;
    private String publishDate;
    private String categoryTagList;
    private String categoryTitleList;

    ArticleFromListBuilder() {
    }

    public ArticleFromListBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleFromListBuilder setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
        return this;
    }

    public ArticleFromListBuilder setPublishDate(String publishDate) {
        this.publishDate = Objects.requireNonNullElse(publishDate, "");
        return this;
    }

    public ArticleFromListBuilder setCategoryTagList(String categoryTagList) {
        List<String> sortedTagList =
                Arrays.stream(categoryTagList.split(", "))
                        .sorted()
                        .collect(Collectors.toList());
        this.categoryTagList = parseListIntoString(sortedTagList);
        return this;
    }

    public ArticleFromListBuilder setCategoryTitleList(String categoryTitleList) {
        List<String> sortedTitleList =
                Arrays.stream(categoryTitleList.split(", "))
                        .sorted()
                        .collect(Collectors.toList());
        this.categoryTitleList = parseListIntoString(sortedTitleList);
        return this;
    }

    public ArticleFromList build() {
        return new ArticleFromList(title, authorFullName, publishDate, categoryTagList, categoryTitleList);
    }
}
