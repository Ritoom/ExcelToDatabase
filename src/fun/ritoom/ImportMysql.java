package fun.ritoom;

import java.sql.*;

public class ImportMysql {
    private static final String URL = "jdbc:mysql://localhost:3306/database";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static String table_name = "";
    static String column_name = "";
    static String command = "";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.Driver");//加载驱动程序
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            //Statement statement = conn.createStatement();
            String sql = "ALTER TABLE "+ table_name +"MODIFY COLUMN "+column_name+" `id` INT( 11 ) COMMENT "+command;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
