package fun.ritoom;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {
    static final String table_name = "table_name";
    static final String file_path = "file.xlsx";

    static Connection conn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("mainview.fxml"));
        primaryStage.setTitle("注释导入工具");
        primaryStage.setScene(new Scene(parent, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
