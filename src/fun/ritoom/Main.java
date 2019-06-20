package fun.ritoom;

import fun.ritoom.dao.ImportMysql;
import fun.ritoom.utils.ReadExcel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    static final String table_name = "table_name";
    static final String file_path = "file.xlsx";
    static final String IP = "";
    static final String DATABASE_POST = "";
    static final String DATABASE = "";
    static final String USER = "";
    static final String PASSWORD = "";
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = ImportMysql.createConn(IP,DATABASE_POST,DATABASE,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("未找到驱动类");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Mysql连接失败");
        }
        try {
            ReadExcel.readExcelFile(table_name,file_path,conn);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取Excel文件失败");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("注释插入失败");
        }
    }
}
