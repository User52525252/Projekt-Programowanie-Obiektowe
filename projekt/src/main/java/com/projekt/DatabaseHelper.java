package com.projekt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:library.db";

    // Służy do tworzenia bazy danych
    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Służy do tworzenia tabeli
    public static void createNewTable() {
        // SQL
        String sql = "CREATE TABLE IF NOT EXISTS books (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " title text NOT NULL,\n"
                + " author text NOT NULL,\n"
                + " isBorrowed integer NOT NULL DEFAULT 0\n"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // łączenie z bazą danych
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
