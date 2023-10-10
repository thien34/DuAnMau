package utils;

import java.sql.*;

public class JdbcHelper {

    private static String hostName = "localhost:1433";
    public static String dbName = "DAM";

    public static String username = "sa";
    public static String password = "123456";

    public static String url = "jdbc:sqlserver://" + hostName + ";DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";

    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = null;
        if (sql.trim().startsWith("{")) {
            ps = conn.prepareCall(sql);
        } else {
            ps = conn.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
        return ps;
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement ps = preparedStatement(sql, args);
            try {
                return ps.executeQuery();
            } finally {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement ps = preparedStatement(sql, args);
            try {
                ps.executeUpdate();
            } finally {
                ps.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
