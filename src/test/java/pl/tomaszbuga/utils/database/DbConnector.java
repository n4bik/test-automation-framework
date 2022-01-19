package pl.tomaszbuga.utils.database;

import java.sql.*;
import java.util.Properties;

public class DbConnector {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5431/postgres";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "b4Dg3r");

        return DriverManager.getConnection(url, props);
    }

}
