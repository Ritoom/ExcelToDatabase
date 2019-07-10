package fun.ritoom.controller;

import fun.ritoom.utils.DbUtils;
import fun.ritoom.utils.ReadExcel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainViewController {
    public Button startButten;
    public Button openFilePath;
    public TextField tableName;
    public TextField filePath;
    public ImageView imageView;
    private Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(MainViewController.class);

    public void openFilePath(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter
                ("EXCEL文件 (*.xlsx)","*.xlsx");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        try {
            String path = file.getPath();
            filePath.setText(path);
        }catch (NullPointerException e){
            logger.error("文件路径未取得");
        }
    }

    public void start(MouseEvent mouseEvent) {
        String table_name = tableName.getText();
        String file_path = filePath.getText();
        logger.info("获取表格:"+table_name+"获取文件路径:"+file_path);
        try {
            conn = DbUtils.linkDb();
            ReadExcel.readExcelFile(table_name, file_path, conn);
            conn.close();
        } catch (IOException e) {
            logger.error("Excel文件未找到");
        } catch (SQLException e) {
            logger.error("数据库连接失败");
        } catch (ClassNotFoundException e) {
            logger.error("数据库驱动未找到");
        }
    }

    public void openSettingPage(ActionEvent actionEvent) throws IOException {
        logger.info("打开配置页面");
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/setting.fxml"));
        Stage stage = new Stage();
        stage.setTitle("设置");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
