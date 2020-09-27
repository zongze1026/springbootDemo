package com.zongze.controller;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create By xzz on 2020-08-18
 */
public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    private static final Integer SHEET_SIZE = 65535;
    private static final int WIDTH = 3766;
    private static final String SHEET_NAME = "sheet";


    /**
     * excel导入数据
     *
     * @param in      输入流
     * @param skipNum 跳过行数
     * @param clazz   需要封装的实体
     */
    @SuppressWarnings("all")
    public static <T> List<T> importExcel(InputStream in, int skipNum, Class<T> clazz, DateTemplate dateTemplate) {
        List<Field> fields = getFields(clazz);
        List<T> data = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTemplate.getTemplate());
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(in);
            HSSFSheet sheet;
            int totalSheet = workbook.getNumberOfSheets();
            if (totalSheet > 0) {
                for (int i = 0; i < totalSheet; i++) {
                    sheet = workbook.getSheetAt(i);
                    if (null != sheet) {
                        int totalRow = sheet.getLastRowNum();
                        for (int n = 0; n <= totalRow; n++) {
                            HSSFRow hssfrow = sheet.getRow(n + skipNum);
                            HSSFCell cell;
                            if (null != hssfrow) {
                                T t = clazz.newInstance();
                                boolean effective = false;
                                for (int m = 0; m < fields.size(); m++) {
                                    cell = hssfrow.getCell(m);
                                    if (null != cell) {
                                        effective = true;
                                        Field field = fields.get(m);
                                        field.setAccessible(true);
                                        setCellValue(dateFormat, t, cell, field);
                                    }
                                }
                                if(effective){
                                    data.add(t);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("数据导入失败", e);
        }
        return data;
    }


    /**
     * @param t
     * @param cell
     * @param field
     * @return void
     */
    @SuppressWarnings("all")
    private static <T> void setCellValue(SimpleDateFormat dateFormat, T t, HSSFCell cell, Field field) throws IllegalAccessException {
        if ((Cell.CELL_TYPE_STRING == cell.getCellType())) {
            doSetFieldValue(t, cell.getStringCellValue(), field);
        } else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                field.set(t, dateFormat.format(cell.getDateCellValue()));
            } else {
                doSetFieldValue(t, String.valueOf(cell.getNumericCellValue()), field);
            }
        }
    }


    @SuppressWarnings("all")
    private static <T> void doSetFieldValue(T t, String cellValue, Field field) throws IllegalAccessException {
        BigDecimal numFormat;
        if (field.getType().equals(String.class)) {
            field.set(t, StringUtils.isEmpty(cellValue) ? null : cellValue);
        } else if (field.getType().equals(Integer.class)) {
            numFormat = new BigDecimal(String.valueOf(cellValue));
            field.set(t, numFormat.intValue());
        } else if (field.getType().equals(Long.class)) {
            numFormat = new BigDecimal(String.valueOf(cellValue));
            field.set(t, numFormat.longValue());
        } else if (field.getType().equals(Double.class)) {
            numFormat = new BigDecimal(String.valueOf(cellValue));
            numFormat = numFormat.setScale(2, RoundingMode.HALF_DOWN);
            field.set(t, numFormat.doubleValue());
        } else if (field.getType().equals(Float.class)) {
            numFormat = new BigDecimal(String.valueOf(cellValue));
            numFormat = numFormat.setScale(2, RoundingMode.HALF_DOWN);
            field.set(t, numFormat.floatValue());
        } else if (field.getType().equals(Byte.class)) {
            numFormat = new BigDecimal(String.valueOf(cellValue));
            field.set(t, numFormat.byteValue());
        }
    }


    /**
     * @param data:数据列表
     * @param response
     * @param clazz:导出的类型
     * @param fileName:excel文件名称
     */
    public static <T> void exportExcel(List<T> data, HttpServletResponse response, Class<? extends
            T> clazz, String fileName) {
        SimpleDateFormat dateFormat;
        HSSFWorkbook workbook = new HSSFWorkbook();
        List<Field> fields = getFields(clazz);

        if (!ObjectUtils.isEmpty(data) && !CollectionUtils.isEmpty(data)) {
            logger.info("开始导出excel表格,数据量：{}", data.size());
            HSSFRow row;
            HSSFCell cell;
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            double sheetNo = Math.ceil(data.size() / SHEET_SIZE);
            for (int index = 0; index <= sheetNo; index++) {
                HSSFSheet sheet = workbook.createSheet();
                workbook.setSheetName(index, SHEET_NAME + index);
                row = sheet.createRow(0);
                buildHeader(workbook, fields, row, sheet);
                //开始写入数据
                int startNo = index * SHEET_SIZE;
                int endNo = Math.min(startNo + SHEET_SIZE, data.size());
                HSSFCellStyle cs = workbook.createCellStyle();
                cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                for (int i = startNo; i < endNo; i++) {
                    row = sheet.createRow(i + 1 - startNo);
                    T vo = data.get(i);
                    for (int j = 0; j < fields.size(); j++) {
                        Field interField = fields.get(j);
                        interField.setAccessible(true);
                        try {
                            cell = row.createCell(j);
                            cell.setCellStyle(cs);
                            Class<?> classType = interField.getType();
                            try {
                                if (classType == Date.class) {
                                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                    cell.setCellValue(dateFormat.format(interField.get(vo)));
                                } else if (classType == String.class) {
                                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                    cell.setCellValue(interField.get(vo) == null ? "" : String.valueOf(interField.get(vo)));
                                } else {
                                    //数字过长就保存成字符串
                                    if (String.valueOf(interField.get(vo)).length() > 10) {
                                        throw new RuntimeException("数字长度过长！");
                                    }
                                    BigDecimal decimal = new BigDecimal(String.valueOf(interField.get(vo)));
                                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(decimal.doubleValue());
                                }
                            } catch (Exception e) {
                                if (vo == null) {
                                    // 如果数据存在就填入,不存在填入空格.
                                    cell.setCellValue("");
                                } else {
                                    // 如果数据存在就填入,不存在填入空格.
                                    cell.setCellValue(interField.get(vo) == null ? "" : String.valueOf(interField.get(vo)));
                                }
                            }

                        } catch (Exception e) {
                            logger.error("excel导出异常", e.getMessage());
                        }
                    }
                }

            }

        } else {
            logger.info("没有可导出的数据");
            HSSFSheet sheet = workbook.createSheet();
            workbook.setSheetName(0, SHEET_NAME + 0);
            HSSFRow row = sheet.createRow(0);
            buildHeader(workbook, fields, row, sheet);
        }
        write(workbook, response, fileName);
    }


    /**
     * 获取需要导入的字段
     *
     * @param clazz
     */
    private static <T> List<Field> getFields(Class<? extends T> clazz) {
        List<Field> fields = new ArrayList<>();
        Field[] fieldList = clazz.getDeclaredFields();
        for (Field field : fieldList) {
            if (field.isAnnotationPresent(Excel.class)) {
                fields.add(field);
            }
        }
        return fields;
    }


    /**
     * 构建表头
     *
     * @param workbook
     * @param fields
     * @param row
     * @param sheet
     */
    private static void buildHeader(HSSFWorkbook workbook, List<Field> fields, HSSFRow row, HSSFSheet sheet) {
        HSSFCell cell;
        for (int n = 0; n < fields.size(); n++) {
            Field field = fields.get(n);
            Excel attr = field.getAnnotation(Excel.class);
            // 创建列
            cell = row.createCell(n);
            // 设置列中写入内容为String类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

            HSSFFont font = workbook.createFont();
            // 粗体显示
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            // 选择需要用到的字体格式
            cellStyle.setFont(font);
            cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            // 设置列宽
            sheet.setColumnWidth(n, WIDTH);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setWrapText(true);
            cell.setCellStyle(cellStyle);
            // 写入列名
            cell.setCellValue(attr.name());
        }
    }

    /**
     * 输出文件流
     *
     * @param workbook
     * @param response
     * @param fileName
     */
    private static void write(HSSFWorkbook workbook, HttpServletResponse response, String fileName) {
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1") + ".xls");
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("excel到处异常", e.getMessage());
        }
    }


    enum DateTemplate {
        DATE("yyyy-MM-dd"),
        DATE_TIME("yyyy-MM-dd HH:mm:ss");

        DateTemplate(String template) {
            this.template = template;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        private String template;
    }


}