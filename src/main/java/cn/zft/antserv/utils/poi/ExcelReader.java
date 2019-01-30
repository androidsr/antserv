package cn.zft.antserv.utils.poi;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public List<String[]> readExcel(String fileName, InputStream stream) throws IOException {
        if (fileName.endsWith(".xlsx")) {
            return readXlsx(stream);
        } else {
            return readXls(stream);
        }
    }

    /**
     * 读取Excel
     *
     * @param is
     * @return
     * @throws IOException
     */
    private List<String[]> readXlsx(InputStream is) throws IOException {
        XSSFWorkbook book = new XSSFWorkbook(is);
        List<String[]> list = new ArrayList<>();
        XSSFSheet sheet = book.getSheetAt(0);
        if (sheet == null) {
            return null;
        }
        String[] obj;
        int celNum;
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = sheet.getRow(rowNum);
            celNum = row.getLastCellNum();
            obj = new String[celNum];
            for (int i = 0; i < celNum; i++) {
                obj[i] = getValue(row.getCell(i));
            }
            is.close();
            list.add(obj);
        }
        return list;
    }

    /*
     *读取Excel
     */
    private List<String[]> readXls(InputStream is) throws IOException {
        HSSFWorkbook book = new HSSFWorkbook(is);
        List<String[]> list = new ArrayList<>();
        HSSFSheet sheet = book.getSheetAt(0);
        if (sheet == null) {
            return null;
        }
        String[] obj;
        int celNum;
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            HSSFRow row = sheet.getRow(rowNum);
            celNum = row.getLastCellNum();
            obj = new String[celNum];
            for (int i = 0; i < celNum; i++) {
                obj[i] = getValue(row.getCell(i));
            }
            list.add(obj);
        }
        is.close();
        return list;
    }

    private String getValue(Cell cell) {
        if (cell == null)
            return "";
        if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.NUMERIC) {
            DecimalFormat df = new DecimalFormat("#.#####");
            String strCell = df.format(cell.getNumericCellValue());
            return strCell;
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

}