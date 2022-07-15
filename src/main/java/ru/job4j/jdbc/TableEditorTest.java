package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditorTest {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream inputStream = TableEditor.class.getClassLoader()
                .getResourceAsStream("postgres.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("cars");
        System.out.println(getTableScheme(tableEditor.getConnection(), "cars"));
        tableEditor.addColumn("cars", "id", "serial primary key");
        System.out.println(getTableScheme(tableEditor.getConnection(), "cars"));
        tableEditor.addColumn("cars", "name", "text");
        System.out.println(getTableScheme(tableEditor.getConnection(), "cars"));
        tableEditor.dropColumn("cars", "name");
        System.out.println(getTableScheme(tableEditor.getConnection(), "cars"));
        tableEditor.renameColumn("cars", "id", "id_car");
        System.out.println(getTableScheme(tableEditor.getConnection(), "cars"));
        tableEditor.dropTable("cars");
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
}
