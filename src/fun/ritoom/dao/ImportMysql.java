package fun.ritoom.dao;

import fun.ritoom.model.TableCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ImportMysql {

    private static final Logger logger = LoggerFactory.getLogger(ImportMysql.class);
    /**
     *
     * @param tableCommand 表格
     * @param table_name 表格名称
     * @param conn 数据库连接
     * @throws SQLException 数据库连接异常
     */
    public static void importMySql(TableCommand tableCommand, String table_name,Connection conn) throws SQLException {
        String sql = "ALTER TABLE "+ table_name +" MODIFY COLUMN "+tableCommand.getColumn_name()+" "+tableCommand.getNote()+" COMMENT "+"\'"+tableCommand.getCommand()+"\'";
        logger.info(sql);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.execute();
    }

    /**
     * Connect to Database
     * @param ip database IP
     * @param database_port 数据库端口
     * @param database 数据库名
     * @param user 用户名
     * @param password 密码
     * @return 返回数据库连接
     * @throws ClassNotFoundException 数据库驱动未取得
     * @throws SQLException 数据库连接异常
     */
    public static Connection createConn(String ip,String database_port,String database,String user,String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");//加载驱动程序
        String conn_url = "jdbc:mysql://"+ip+":"+database_port+"/"+database;
        logger.info("创建MySql连接");
        return DriverManager.getConnection(conn_url,user,password);
    }
}
