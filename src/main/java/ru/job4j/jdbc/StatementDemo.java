package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class StatementDemo {
    private static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = StatementDemo.class.getClassLoader()
                .getResourceAsStream("postgres.properties");
        properties.load(inputStream);
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        return DriverManager.getConnection(properties.getProperty("hibernate.connection.url"),
                properties.getProperty("hibernate.connection.username"),
                properties.getProperty("hibernate.connection.password"));
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name text"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }
}
