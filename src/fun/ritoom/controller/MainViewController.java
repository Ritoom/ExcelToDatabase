package fun.ritoom.controller;

import fun.ritoom.utils.DbUtils;
import fun.ritoom.utils.ReadExcel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class MainViewController {
    public Button startButten;
    public Button openFilePath;
    public TextField tableName;
    public TextField filePath;
    private Connection conn;

    public void openFilePath(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter
                ("EXCEL文件 (*.xlsx)","*.xlsx");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        String path = file.getPath();
        filePath.setText(path);
    }

    public void start(MouseEvent mouseEvent) {
        String table_name = tableName.getText();
        String file_path = filePath.getText();
        System.out.println(table_name);
        System.out.println(file_path);
        try {
            conn = DbUtils.linkDb();
            ReadExcel.readExcelFile(table_name, file_path, conn);
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件未找到");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动未找到");
        }
    }

    public void openSettingPage(ActionEvent actionEvent) throws IOException {
        System.out.println("打开配置页面");
        Parent parent = FXMLLoader.load(getClass().getResource("setting.fxml"));
        Stage stage = new Stage();
        stage.setTitle("设置");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
