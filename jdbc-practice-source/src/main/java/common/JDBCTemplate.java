package common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    // Connection 생성 및 자원 반납을 위한 static method 작성
    public static Connection getConnection() {
        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("jdbc-practice-source/src/main/java/config/connection-info.properties"));
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);
            con = DriverManager.getConnection(url, prop);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static void close(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void close(ResultSet rset) {
        try {
            if (rset != null && !rset.isClosed()) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
