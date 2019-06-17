package fun.ritoom;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class ReadExcel {
    public static void main(String[] args) throws IOException {
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        FileOutputStream outputStream = new FileOutputStream(new File("file.xlsx"));
//        workbook.write(outputStream);
//        outputStream.close();
//        System.out.println("创建成功");
        readExcelFile();
    }

    static XSSFRow row;
    public static void readExcelFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("file.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()){
            row = (XSSFRow)rowIterator.next();
            Iterator <Cell>  cellIterator = row.cellIterator();

            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();

                switch (cell.getCellType()){
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + " \t\t ");
                        break;

                    case Cell.CELL_TYPE_STRING:
                        System.out.print(
                                cell.getStringCellValue() + " \t\t ");
                        break;
                }
            }
            System.out.println();
        }
        fileInputStream.close();
    }
}
