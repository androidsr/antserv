package cn.zft.antserv.utils.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ExcelWriter {
    public static final int DATA_SIZE = 1000;

    /**
     * 分页数据加载
     *
     * @return
     */
    public abstract List<Map<String, String>> loadData();

    /**
     * 标题设置
     *
     * @return
     */
    public abstract String title();

    /**
     * 列表头标题设置
     *
     * @return
     */
    public abstract String[] titleArray();

    /**
     * 统计列设置，如：A,B,C
     *
     * @return
     */
    public abstract String isTotal();

    /**
     * 默认标题配置
     *
     * @param workbook
     * @param sheet
     */
    public void configTitle(XSSFWorkbook workbook, XSSFSheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) 600);
        Cell c = row.createCell(0);
        CellStyle style = Style.setTitleStyle(workbook, c);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        c.setCellStyle(style);
        c.setCellValue(title());

        row = sheet.createRow(1);
        row.setHeight((short) 400);
        String[] title = titleArray();
        for (int i = 0; i < title.length; i++) {
            c = row.createCell(i);
            Style.setTitleStyle(workbook, c);
            c.setCellValue(title[i]);
        }
    }

    /**
     * 默认单元格配置
     *
     * @param workbook
     * @param cell
     */
    public void configCell(XSSFWorkbook workbook, Cell cell) {
        Style.setDataStyle(workbook, cell);
    }

    public XSSFWorkbook build() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        sheet.setDefaultColumnWidth((short) 15);
        sheet.setVerticallyCenter(true);
        configTitle(workbook, sheet);
        XSSFRow row;
        List<Map<String, String>> data = loadData();
        int rowIndex = sheet.getLastRowNum() + 1;
        int i = 0;
        String isSum = isTotal();
        Map<Integer, BigDecimal> sumData = new HashMap<>();
        int cellSum;
        while (true) {
            Map<String, String> rowData = data.get(i++);
            cellSum = rowData.size();
            row = sheet.createRow(rowIndex++);
            int j = 0;
            for (String key : rowData.keySet()) {
                String val = rowData.get(key);
                if (isSum.contains(key)) {
                    BigDecimal old = sumData.get(j);
                    if (old == null) old = new BigDecimal(0);
                    sumData.put(j, old.add(new BigDecimal(val)));
                }
                Cell c = row.createCell(j++);
                configCell(workbook, c);
                if (!StringUtils.isEmpty(val)) {
                    c.setCellValue(val);
                }
            }
            //最大退出数
            if (sheet.getLastRowNum() > 100000) {
                break;
            }
            //重新加载数据或退出
            if (data.size() == i) {
                data = loadData();
                i = 0;
                if (data.size() == 0)
                    break;
            }
        }
        //统计列表数据
        row = sheet.createRow(sheet.getLastRowNum() + 1);
        for (int j = 0; j < cellSum; j++) {
            Cell c = row.createCell(j);
            Style.setTitleStyle(workbook, c);
            if (j == 0) c.setCellValue("合计");
        }
        Cell c;
        for (Integer key : sumData.keySet()) {
            c = row.createCell(key);
            Style.setTitleStyle(workbook, c);
            c.setCellValue(sumData.get(key).doubleValue());
        }
        return workbook;
    }

    public void write(XSSFWorkbook workbook, HttpServletResponse response) throws IOException {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(title().getBytes("UTF-8"), "iso-8859-1") + ".xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    public static class Style {

        public static CellStyle setTitleStyle(XSSFWorkbook workbook, Cell cell) {
            CellStyle style = setDataStyle(workbook, cell);
            // 填充色
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // 字体
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            return cell.getCellStyle();
        }

        public static CellStyle setDataStyle(XSSFWorkbook workbook, Cell cell) {
            XSSFCellStyle style = workbook.createCellStyle();
            cell.setCellStyle(style);

            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());

            return cell.getCellStyle();
        }
    }
}
