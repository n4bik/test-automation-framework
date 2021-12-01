package pl.tomaszbuga.utils;

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

}
