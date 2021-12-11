package pl.tomaszbuga.tests.models.article;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Article {
    private String title;
    private String authorFullName;
    private String publishDate;
    private String categoryTagList;
    private String categoryTitleList;

    public Article() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = Objects.requireNonNullElse(publishDate, "");
    }

    public void setCategoryTagList(String categoryTagList) {
        List<String> sortedTagList =
                Arrays.stream(categoryTagList.split(", "))
                        .sorted()
                        .collect(Collectors.toList());
        this.categoryTagList = String.join(", ", sortedTagList);
    }

    public void setCategoryTitleList(String categoryTitleList) {
        List<String> sortedTitleList =
                Arrays.stream(categoryTitleList.split(", "))
                        .sorted()
                        .collect(Collectors.toList());
        this.categoryTitleList = String.join(", ", sortedTitleList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!title.equals(article.title)) return false;
        if (!authorFullName.equals(article.authorFullName)) return false;
        if (!publishDate.equals(article.publishDate)) return false;
        if (!categoryTagList.equals(article.categoryTagList)) return false;
        return categoryTitleList.equals(article.categoryTitleList);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + authorFullName.hashCode();
        result = 31 * result + publishDate.hashCode();
        result = 31 * result + categoryTagList.hashCode();
        result = 31 * result + categoryTitleList.hashCode();
        return result;
    }
}










//    public void setPublishDate(String publishDate) {
//        this.publishDate = Objects.requireNonNullElse(publishDate, "");
//    }
//
//    public void setCategoryTagList(String categoryTagList) {
//        List<String> sortedTagList =
//                Arrays.stream(categoryTagList.split(", "))
//                        .sorted()
//                        .collect(Collectors.toList());
//        this.categoryTagList = String.join(", ", sortedTagList);
//    }
//
//    public void setCategoryTitleList(String categoryTitleList) {
//        List<String> sortedTitleList =
//                Arrays.stream(categoryTitleList.split(", "))
//                        .sorted()
//                        .collect(Collectors.toList());
//        this.categoryTitleList = String.join(", ", sortedTitleList);
//    }
//
//}