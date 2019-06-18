package fun.ritoom;

import java.sql.*;

public class ImportMysql {
    private static final String URL = "jdbc:mysql://192.168.0.55:3306/yw_database";
    private static final String USER = "root";
    private static final String PASSWORD = "shunguomysql";

    public static void importMySql(TableCommand tableCommand,String table_name){
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动程序
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            //Statement statement = conn.createStatement();
            //String sql = "ALTER TABLE "+ table_name +"MODIFY COLUMN "+column_name+" `id` INT( 11 ) COMMENT "+command;
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
