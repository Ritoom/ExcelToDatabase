package fun.ritoom;

import fun.ritoom.model.TableCommand;

import java.sql.*;

public class ImportMysql {
    private static final String URL = "jdbc:mysql://localhost:3306/database";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static void importMySql(TableCommand tableCommand, String table_name){
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动程序
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String sql = "ALTER TABLE "+ table_name +" MODIFY COLUMN "+tableCommand.column_name+" "+tableCommand.getNote()+" COMMENT "+"\'"+tableCommand.command+"\'";
            System.out.println(sql);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
