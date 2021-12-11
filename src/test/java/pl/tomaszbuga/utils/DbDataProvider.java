package pl.tomaszbuga.utils;

import pl.tomaszbuga.tests.models.article.Article;

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

    public static List<Article> getArticlesListByCategoryId(String categoryId) {
        List<Article> articleList = new ArrayList<>();

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
                Article article = new Article();
                article.setTitle(resultSet.getString(1));
                article.setAuthorFullName(resultSet.getString(2));
                article.setPublishDate(resultSet.getString(3));
                article.setCategoryTagList(resultSet.getString(4));
                article.setCategoryTitleList(resultSet.getString(5));

                articleList.add(article);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articleList;
    }

    public static Article getArticleDetails(String articleId) {
        Article article = new Article();

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
                article.setTitle(resultSet.getString(1));
                article.setPublishDate(resultSet.getString(2));
                article.setAuthorFullName(resultSet.getString(3));
                article.setSummary(resultSet.getString(4));
                article.setContent(resultSet.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return article;
    }
}
