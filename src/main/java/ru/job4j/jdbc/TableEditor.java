package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();

    }

    public Connection getConnection() {
        return connection;
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(properties.getProperty("hibernate.connection.url"),
                    properties.getProperty("hibernate.connection.username"),
                    properties.getProperty("hibernate.connection.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s ();",
                tableName
        );
        execute(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "drop table if exists %s;",
                tableName
        );
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "alter table %s add column %s %s",
                tableName, columnName, type
        );
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "alter table %s drop column %s",
                tableName, columnName
        );
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "alter table %s rename column %s to %s",
                tableName, columnName, newColumnName
        );
        execute(sql);
    }

    private void execute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
