package pl.tomaszbuga.utils;

import pl.tomaszbuga.tests.models.article.ArticleDetails;
import pl.tomaszbuga.tests.models.article.ArticleFromList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static pl.tomaszbuga.utils.DbConnector.getConnection;

public class DbDataProvider {

    public static List<String> getCategoryTitles() {
        List<String> titleList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT title FROM category")) {

            while (resultSet.next()) {
                String categoryTitle = resultSet.getString(1);
                titleList.add(categoryTitle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titleList;
    }

    public static List<ArticleFromList> getArticlesListByCategoryId(String categoryId) {
        List<ArticleFromList> articleList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT article.title,\n" +
                     "       CONCAT(author_first_name, ' ', author_last_name) as author_full_name,\n" +
                     "       article.publish_date,\n" +
                     "       string_agg(category.tag, ', ')                   as category_tag_list,\n" +
                     "       string_agg(category.title, ', ')                 as category_title_list\n" +
                     "FROM category,\n" +
                     "     article_category,\n" +
                     "     (\n" +
                     "         SELECT article_id as aid\n" +
                     "         FROM article_category\n" +
                     "         WHERE category_id = " + categoryId + "\n" +
                     "     ) as ac\n" +
                     "         JOIN article\n" +
                     "              ON article.id = ac.aid\n" +
                     "WHERE article.id = article_category.article_id\n" +
                     "  AND category.id = article_category.category_id\n" +
                     "GROUP BY 1, 2, 3;")) {

            while (resultSet.next()) {
                ArticleFromList articleFromList = ArticleFromList.builder()
                        .setTitle(resultSet.getString(1))
                        .setAuthorFullName(resultSet.getString(2))
                        .setPublishDate(resultSet.getString(3))
                        .setCategoryTagList(resultSet.getString(4))
                        .setCategoryTitleList(resultSet.getString(5))
                        .build();

                articleList.add(articleFromList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articleList;
    }

    public static ArticleDetails getArticleDetails(String articleId) {
        ArticleDetails articleDetails = null;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT article.title,\n" +
                             "       article.publish_date,\n" +
                             "       CONCAT(author_first_name, ' ', author_last_name) as author_full_name,\n" +
                             "       article.summary,\n" +
                             "       article.content\n" +
                             "FROM article\n" +
                             "WHERE article.id = " + articleId + ";")) {

            while (resultSet.next()) {
                articleDetails = ArticleDetails.builder()
                        .setTitle(resultSet.getString(1))
                        .setPublishDate(resultSet.getString(2))
                        .setAuthorFullName(resultSet.getString(3))
                        .setSummary(resultSet.getString(4))
                        .setContent(resultSet.getString(5))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articleDetails;
    }
}
