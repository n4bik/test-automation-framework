package pl.tomaszbuga.utils.database;

import io.qameta.allure.Step;
import pl.tomaszbuga.tests.models.article.Article;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static pl.tomaszbuga.utils.database.DbConnector.getConnection;
import static pl.tomaszbuga.utils.database.SqlQueriesProvider.*;

public class DbDataProvider {

    @Step("Get Category titles from Database")
    public static List<String> getCategoryTitles() {
        List<String> titleList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getCategoryTitlesQuery())) {

            while (resultSet.next()) {
                String categoryTitle = resultSet.getString(1);
                titleList.add(categoryTitle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titleList;
    }

    @Step("Get Articles list from Database for Category ID: {categoryId}")
    public static List<Article> getArticlesListByCategoryId(String categoryId) {
        List<Article> articleList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getArticlesListByCategoryIdQuery(categoryId))) {

            while (resultSet.next()) {
                Article articleFromList = Article.builder()
                        .title(resultSet.getString(1))
                        .authorFullName(resultSet.getString(2))
                        .publishDate(resultSet.getString(3))
                        .categoryTagList(resultSet.getString(4))
                        .categoryTitleList(resultSet.getString(5))
                        .build();

                articleList.add(articleFromList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articleList;
    }

    @Step("Get Article details from Database for article ID: {articleId}")
    public static Article getArticleDetails(String articleId) {
        Article articleDetails = null;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getArticleDetailsQuery(articleId))) {

            while (resultSet.next()) {
                articleDetails = Article.builder()
                        .title(resultSet.getString(1))
                        .publishDate(resultSet.getString(2))
                        .authorFullName(resultSet.getString(3))
                        .summary(resultSet.getString(4))
                        .content(resultSet.getString(5))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articleDetails;
    }

}
