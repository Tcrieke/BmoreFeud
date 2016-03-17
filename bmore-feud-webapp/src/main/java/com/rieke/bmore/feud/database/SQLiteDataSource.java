package com.rieke.bmore.feud.database;

import com.rieke.bmore.feud.category.Category;
import com.rieke.bmore.feud.category.CategoryService;
import com.rieke.bmore.feud.importer.ImportService;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manages the database schema in SQLite for the ExternalFileWatcher
 *
 * Created by tylerrieke on 3/2/16.
 */
public class SQLiteDataSource {

    private static DataSource dataSource;
    private static ImportService importService;
    private static CategoryService categoryService;

    public SQLiteDataSource(DataSource dataSource, ImportService importService) {
        this.dataSource = dataSource;
        this.importService = importService;
    }

    @PostConstruct
    public void initialize() {

        boolean newDB = createDatabase();
        if(newDB) {
            try {
                InputStream in = SQLiteDataSource.class.getClassLoader().getResourceAsStream("/bmorefeud_data.txt");
                BufferedReader buffered = new BufferedReader(new InputStreamReader(in, Charset.defaultCharset()));

                importService.parseFile(buffered);
            } catch (Exception e) {
                System.out.println("FAILED to load data into new database.");
            }
        }
    }

    private static boolean createCategoryTable(Statement statement) {
        boolean created = false;
        try {
            statement.executeUpdate(
                    "CREATE TABLE `category` (" +
                            "  `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "  `name` varchar(700) NOT NULL," +
                            "  `last_used` datetime NOT NULL," +
                            "  UNIQUE(`name`)" +
                            ");"
            );
            created = true;
        } catch (SQLException e) {
            System.out.println("Table 'category' already exists");
        }
        return created;
    }

    private static void dropCategoryTable(Statement statement) {
        try {
            statement.executeUpdate(
                    "DROP TABLE IF EXISTS category;"
            );
        } catch (SQLException e) {
            System.out.println("Failed to drop table 'category'");
        }
    }

    private static void createValueTable(Statement statement) {
        try {
            statement.executeUpdate(
                    "CREATE TABLE `value` (" +
                            "  `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "  `category_id` int(11) NOT NULL," +
                            "  `value` varchar(25) NOT NULL," +
                            "  `points` int(11)," +
                            "  UNIQUE(`category_id`,`value`)," +
                            "  CONSTRAINT `value_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION" +
                            ");"
            );
        } catch (SQLException e) {
            System.out.println("Table 'value' already exists");
        }
    }

    private static void dropValueTable(Statement statement) {
        try {
            statement.executeUpdate(
                    "DROP TABLE IF EXISTS `value`;"
            );
        } catch (SQLException e) {
            System.out.println("Failed to drop table 'value'");
        }
    }

    private static void createAcceptedTable(Statement statement) {
        try {
            statement.executeUpdate(
                    "CREATE TABLE `accepted` (" +
                            "  `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "  `value_id` int(11)," +
                            "  `value` varchar(25) NOT NULL," +
                            "  CONSTRAINT `accepted_value_id` FOREIGN KEY (`value_id`) REFERENCES `value` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION" +
                            ");"
            );
        } catch (SQLException e) {
            System.out.println("Table 'accepted' already exists");
        }
    }

    private static void dropAcceptedTable(Statement statement) {
        try {
            statement.executeUpdate(
                    "DROP TABLE IF EXISTS `accepted`;"
            );
        } catch (SQLException e) {
            System.out.println("Failed to drop table 'accepted'");
        }
    }


    public static void resetDatabase() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            dropAcceptedTable(statement);
            dropValueTable(statement);
            dropCategoryTable(statement);
            createCategoryTable(statement);
            createValueTable(statement);
            createAcceptedTable(statement);

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("Failed to create database connection");
        }
    }

    public static boolean createDatabase() {
        boolean newDB = false;
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            newDB = createCategoryTable(statement);
            createValueTable(statement);
            createAcceptedTable(statement);

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("Failed to create database connection");
        }
        return newDB;
    }

    public static void dropDatabase() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            dropAcceptedTable(statement);
            dropValueTable(statement);
            dropCategoryTable(statement);


            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("Failed to create database connection");
        }
    }
}
