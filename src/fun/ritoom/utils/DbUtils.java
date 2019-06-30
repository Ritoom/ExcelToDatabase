package fun.ritoom.utils;

import fun.ritoom.dao.ImportMysql;
import fun.ritoom.model.Setting;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class DbUtils {

    public static Connection linkDb() throws SQLException, ClassNotFoundException {
        Preferences preferences = Preferences.userNodeForPackage(Setting.class);
        String IP = preferences.get("ip", "localhost");
        String DATABASE_POST = preferences.get("port", "3306");
        String DATABASE = preferences.get("database", "sys");
        String USER = preferences.get("user", "root");
        String PASSWORD = preferences.get("password", "root");
        return ImportMysql.createConn(IP, DATABASE_POST, DATABASE, USER, PASSWORD);
    }
}
