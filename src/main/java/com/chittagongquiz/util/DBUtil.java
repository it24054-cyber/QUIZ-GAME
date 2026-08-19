package com.chittagongquiz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Central place to open a JDBC connection to the quiz database.
 *
 * Reads connection details from environment variables so the SAME code
 * works both locally (XAMPP) and on a host like Railway:
 *   DB_HOST      e.g. localhost  or  containers-us-west-1.railway.app
 *   DB_PORT      e.g. 3306
 *   DB_NAME      e.g. chittagong_quiz_db  or  railway
 *   DB_USER      e.g. root
 *   DB_PASSWORD  e.g. (empty locally) or the password Railway gives you
 *
 * If an env var isn't set, it falls back to your local XAMPP defaults,
 * so nothing changes for local development.
 */
public class DBUtil {

    private static final String HOST     = getEnv("DB_HOST", "localhost");
    private static final String PORT     = getEnv("DB_PORT", "3306");
    private static final String DB_NAME  = getEnv("DB_NAME", "chittagong_quiz_db");
    private static final String USER     = getEnv("DB_USER", "root");
    private static final String PASSWORD = getEnv("DB_PASSWORD", "");

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?useSSL=false&serverTimezone=UTC";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found. Add mysql-connector-j to WEB-INF/lib.", e);
        }
    }

    private static String getEnv(String key, String fallback) {
        String value = System.getenv(key);
        return (value == null || value.isEmpty()) ? fallback : value;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
