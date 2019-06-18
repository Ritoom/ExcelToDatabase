package fun.ritoom;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadExcel {
    public static void main(String[] args) throws IOException {
        readExcelFile();
    }

    static XSSFRow row;
    static ArrayList<TableCommand> stringsColumn = new ArrayList<>();
    static String table_name = "rf_caigxjgxx";
    public static void readExcelFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("file_new.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        for(int i=sheet.getFirstRowNum();i<sheet.getLastRowNum();i++){
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
//        while (rowIterator.hasNext()){
//            row = (XSSFRow)rowIterator.next();
//            Iterator <Cell>  cellIterator = row.cellIterator();
//            String s = null;
//
//            TableCommand tableCommand = new TableCommand();
//            while (cellIterator.hasNext()){
//                Cell cell = cellIterator.next();
//
//                switch (cell.getCellType()){
//                    case Cell.CELL_TYPE_STRING:
//                        s = cell.getStringCellValue();
//                        stringsColumn.add(s);
//                        break;
//                }
//                //每列
//                System.out.println(stringsColumn);
//            }
//            //每行
//            //System.out.println(strings.get(strings.size() -1).toString());
//            //String[] culumn = s.split(" ");
////            tableCommand.setTable_name(strings);
////            tableCommand.setColumn_name(culumn[1]);
////            tableCommand.setCommand(culumn[2]);
////            System.out.println(tableCommand.toString());
//
//            //ImportMysql.importMySql(tableCommand);
        //}
        //fileInputStream.close();
    }
}
