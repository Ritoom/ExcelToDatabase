package fun.ritoom.utils;

import fun.ritoom.dao.ImportMysql;
import fun.ritoom.model.TableCommand;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadExcel {

    private static ArrayList<TableCommand> stringsColumn = new ArrayList<>();

    public static void readExcelFile(String table_name, String file_path, Connection conn) throws IOException, SQLException {
        FileInputStream fileInputStream = new FileInputStream(new File(file_path));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        int sheet_num = 0;
        XSSFSheet sheet = workbook.getSheetAt(sheet_num);

        for(int i=sheet.getFirstRowNum();i<=sheet.getLastRowNum();i++){
            TableCommand tableCommand= new TableCommand();
            Row row = sheet.getRow(i);
            for (int j=row.getFirstCellNum();j<=row.getLastCellNum();j++){
                Cell cell = row.getCell(j);
                if (j == 0){
                    tableCommand.setColumn_name(cell.getStringCellValue());
                }
                if (j == 1){
                    tableCommand.setNote(cell.getStringCellValue());
                }
                if (j == 2){
                    tableCommand.setCommand(cell.getStringCellValue());
                }
            }
            stringsColumn.add(tableCommand);
        }

        for (TableCommand str :
                stringsColumn) {
            if (str.getColumn_name().length() > 0 && str.getColumn_name() != null){
                ImportMysql.importMySql(str,table_name,conn);
            }
        }
        fileInputStream.close();
    }
}
