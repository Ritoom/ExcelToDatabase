package fun.ritoom.dao;

import fun.ritoom.model.TableCommand;

import java.sql.*;

public class ImportMysql {

    public static void importMySql(TableCommand tableCommand, String table_name,Connection conn) throws SQLException {
        //Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
        String sql = "ALTER TABLE "+ table_name +" MODIFY COLUMN "+tableCommand.getColumn_name()+" "+tableCommand.getNote()+" COMMENT "+"\'"+tableCommand.getCommand()+"\'";
        System.out.println(sql);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.execute();
    }

    public static Connection createConn(String ip,String database_port,String database,String user,String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");//加载驱动程序
        String conn_url = "jdbc:mysql://"+ip+":"+database_port+"/"+database;
        System.out.println("创建Mysql连接");
        return DriverManager.getConnection(conn_url,user,password);
    }
}
