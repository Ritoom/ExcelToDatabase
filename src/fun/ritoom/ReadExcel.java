package fun.ritoom;

import fun.ritoom.model.TableCommand;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class ReadExcel {
    public static void main(String[] args) throws IOException {
        readExcelFile();
    }

    static XSSFRow row;
    static ArrayList<TableCommand> stringsColumn = new ArrayList<>();
    static String table_name = "table_name";
    static String file_path = "file.xlsx";
    static int sheet_num = 0;
    public static void readExcelFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(file_path));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
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
            System.out.println(str.toString());
            if (str.getColumn_name().length() > 0 && str.getColumn_name() != null){
                ImportMysql.importMySql(str,table_name);
            }
        }
        fileInputStream.close();
    }
}
