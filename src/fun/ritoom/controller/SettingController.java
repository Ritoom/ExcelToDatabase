package fun.ritoom.controller;

import fun.ritoom.dao.ImportMysql;
import fun.ritoom.model.Setting;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class SettingController {
    private static final Logger logger = LoggerFactory.getLogger(SettingController.class);
    public TextField ip;
    public TextField port;
    public TextField database;
    public TextField user;
    public PasswordField password;
    private Connection connection;

    public void save(MouseEvent mouseEvent) {
        Preferences pref = Preferences.userNodeForPackage(Setting.class);
        pref.put("ip",ip.getText());
        pref.put("port",port.getText());
        pref.put("database",database.getText());
        pref.put("user",user.getText());
        pref.put("password",password.getText());
        logger.info("保存ip地址为:"+ip.getText());
    }

    public void testLink(MouseEvent mouseEvent) {
        try {
            String ip_text = ip.getText();
            String port_text = port.getText();
            String database_text = database.getText();
            String user_text = user.getText();
            String password_text = password.getText();
            connection = ImportMysql.createConn(ip_text, port_text, database_text, user_text, password_text);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("连接失败");
            alert.setContentText("数据库连接失败!");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (!connection.isClosed()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("连接成功");
                alert.setContentText("数据库连接成功!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
