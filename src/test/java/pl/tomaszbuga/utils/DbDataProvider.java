package pl.tomaszbuga.utils;

import pl.tomaszbuga.tests.models.article.Article;
import pl.tomaszbuga.tests.models.article.ArticleBuilder;

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

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT title FROM category");

            while (resultSet.next()) {
                String categoryTitle = resultSet.getString(1);
                titleList.add(categoryTitle);
            }

            teardown(connection, statement, resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titleList;
    }

    public static List<Article> getArticlesList() {
        List<Article> articleList = new ArrayList<>();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM article");

            while (resultSet.next()) {
                Article article = new ArticleBuilder()
                        .setId(resultSet.getString(4))
                        .setTitle(resultSet.getString(1))
                        .setAuthorFirstName(resultSet.getString(2))
                        .setAuthorLastName(resultSet.getString(3))
                        .setSummary(resultSet.getString(5))
                        .setContent(resultSet.getString(6))
                        .setCreateDate(resultSet.getString(7))
                        .setPublishDate(resultSet.getString(8))
                        .build();

                articleList.add(article);
            }

            teardown(connection, statement, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articleList;
    }

    public static List<Article> getArticlesListByCategoryId(String categoryId) {
        List<Article> articleList = new ArrayList<>();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT article.title,\n" +
                            "       article.author_first_name,\n" +
                            "       article.author_last_name,\n" +
                            "       article.publish_date\n" +
                            "FROM (\n" +
                            "         SELECT article_id as aid\n" +
                            "         FROM article_category\n" +
                            "         WHERE category_id = 19\n" +
                            "     ) as ac\n" +
                            "         JOIN article\n" +
                            "              ON article.id = ac.aid");

            while (resultSet.next()) {
                Article article = new ArticleBuilder()
                        .setTitle(resultSet.getString(1))
                        .setAuthorFirstName(resultSet.getString(2))
                        .setAuthorLastName(resultSet.getString(3))
                        .setPublishDate(resultSet.getString(4))
                        .build();

                articleList.add(article);
            }

            teardown(connection, statement, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articleList;
    }

    private static void teardown(Connection connection, Statement statement, ResultSet resultSet)
            throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }


}
