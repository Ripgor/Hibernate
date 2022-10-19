package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String URL = "jdbc:mysql://localhost:3306/pp_1";
    public static final String LOGIN = "root";
    public static final String PASSWORD = "root";
    public static Connection getConnect() {

        try {
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}