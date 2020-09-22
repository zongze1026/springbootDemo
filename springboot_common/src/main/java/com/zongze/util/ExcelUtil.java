package com.zongze.util;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import com.zongze.annotation.Excel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create By xzz on 2020-08-18
 */
public class ExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    private static final Integer SHEET_SIZE = 65535;  //excel支持最大行数
    private static final int WIDTH = 3766;   //列宽
    private static final String SHEET_NAME = "sheet";   //页名称


    /**
     * excel导入数据
     *
     * @param in      输入流
     * @param skipNum 跳过行数
     * @param clazz   需要封装的实体
     */
    public static <T> List<T> importExcel(InputStream in, int skipNum, Class<T> clazz) {
        List<Field> fields = getFields(clazz);
        List<T> data = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(in);
            HSSFSheet sheet;
            int totalSheet = workbook.getNumberOfSheets();
            if (totalSheet > 0) {
                for (int i = 0; i < totalSheet; i++) {
                    sheet = workbook.getSheetAt(i);
                    if(null != sheet){
                        int totalRow = sheet.getLastRowNum();
                        T t = clazz.newInstance();
                        for (int n = 0; n < totalRow; n++) {
                            HSSFRow hssfrow = sheet.getRow(n + skipNum);
                            HSSFCell cell;
                            for (int m = 0; m < fields.size(); m++) {
                                cell= hssfrow.getCell(m);
                                Field field = fields.get(m);
                                field.setAccessible(true);
                                if (field.getType().equals(String.class)) {
                                    field.set(t, cell.getStringCellValue());
                                } else if (field.getType().equals(Integer.class)) {
                                    field.set(t, Integer.valueOf(cell.getStringCellValue()));
                                } else if (field.getType().equals(Long.class)) {
                                    field.set(t, Long.valueOf(cell.getStringCellValue()));
                                } else if (field.getType().equals(Double.class)) {
                                    field.set(t, Double.valueOf(cell.getStringCellValue()));
                                } else if (field.getType().equals(Float.class)) {
                                    field.set(t, Float.valueOf(cell.getStringCellValue()));
                                } else if (field.getType().equals(Boolean.class)) {
                                    field.set(t, Boolean.valueOf(cell.getStringCellValue()));
                                } else if (field.getType().equals(Byte.class)) {
                                    field.set(t, Byte.valueOf(cell.getStringCellValue()));
                                } else {
                                    field.set(t, dateFormat.parse(cell.getStringCellValue()));
                                }
                                data.add(t);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    /**
     * @param data:数据列表
     * @param response
     * @param clazz:导出的类型
     * @param fileName:excel文件名称
     */
    public static <T> void exportExcel(List<T> data, HttpServletResponse response, Class<? extends T> clazz, String fileName) {
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

}