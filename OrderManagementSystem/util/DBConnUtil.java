package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
    public static Connection getDBConn() throws SQLException, IOException {
        String dbUrl = DBPropertyUtil.getConnectionString("db.properties");
        return DriverManager.getConnection(dbUrl);
    }
}
