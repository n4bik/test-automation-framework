package pl.tomaszbuga.tests.models.article;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNullElse;
import static pl.tomaszbuga.utils.StringUtils.parseListIntoString;

@Builder
@EqualsAndHashCode
public class Article {
    private final String title;
    private final String authorFullName;
    private final String publishDate;
    private final String summary;
    private final String content;

    public static class ArticleBuilder {
        private String publishDate;
        private String categoryTagList;
        private String categoryTitleList;

        public ArticleBuilder publishDate(String publishDate) {
            this.publishDate = requireNonNullElse(publishDate, "");
            return this;
        }

        public ArticleBuilder categoryTagList(String categoryTagList) {
            List<String> sortedTagList =
                    Arrays.stream(categoryTagList.split(", "))
                            .sorted()
                            .collect(Collectors.toList());
            this.categoryTagList = requireNonNullElse(parseListIntoString(sortedTagList), "");
            return this;
        }

        public ArticleBuilder categoryTitleList(String categoryTitleList) {
            List<String> sortedTitleList =
                    Arrays.stream(categoryTitleList.split(", "))
                            .sorted()
                            .collect(Collectors.toList());
            this.categoryTitleList = requireNonNullElse(parseListIntoString(sortedTitleList), "");
            return this;
        }
    }

}