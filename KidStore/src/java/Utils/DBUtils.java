package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static final String DB_NAME = "KidStore";
    private static final String DB_USER_NAME = "sa";
    private static final String DB_PASSWORD = "12345";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String sql = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
        conn = DriverManager.getConnection(sql, DB_USER_NAME, DB_PASSWORD);
        return conn;
    }

}
