package DAL;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nguyễn Đăng Dũng on 3/7/2017 9:36 AM
 * Project: BaiTapLon
 */
public class Database {
    private static String HOST = Env.envVar.get("DB_HOST");
    private static String PORT = Env.envVar.get("DB_PORT");
    private static String INSTANCE = Env.envVar.get("DB_INSTANCE");
    private static String USER = Env.envVar.get("DB_USERNAME");
    private static String PASS = Env.envVar.get("DB_PASSWORD");
    private static String DBNAME = Env.envVar.get("DB_NAME");
    private Connection connection = null;

    public static boolean MySql(String host, String port, String dbName, String user, String pass) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8";
        return testConnect(url, user, pass);

    }

    public static boolean SqlServer(String host, String port, String instance, String dbName, String user, String pass) {
        String url = "jdbc:sqlserver://" + host + ":" + port
                + ";instance=" + instance + ";databaseName=" + dbName;
        return testConnect(url, user, pass);
    }

    private static boolean testConnect(String url, String user, String pass) {
        try {
            //Java 6 trở đi là không cần thiết
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private String getUrl() {
        if (Env.envVar.get("TYPE_DB").equalsIgnoreCase("sqlserver")) {
            return "jdbc:sqlserver://" + HOST + ":" + PORT
                    + ";instance=" + INSTANCE + ";databaseName=" + DBNAME;
        } else {
            return "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME + "?useUnicode=true&characterEncoding=UTF-8";
        }
    }

    public Connection connect() throws ConnectException {
        try {
            //Java 6 trở đi là không cần thiết
            //Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(getUrl(), USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectException();
        }
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
