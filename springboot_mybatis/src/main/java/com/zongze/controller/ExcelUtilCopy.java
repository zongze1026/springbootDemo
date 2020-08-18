package com.zongze.controller;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create By xzz on 2018/11/1
 */
public class ExcelUtilCopy {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtilCopy.class);

    private static final Integer SHEET_SIZE = 65535;  //最大行数

    private static final int WIDTH = 3766;   //列宽

    private static final String SHEET_NAME = "sheet";   //页名称


    /**
     * @param data:数据列表
     * @param response
     * @param clazz:导出的类型
     * @param fileName:excel文件名称
     */
    public static <T> void exportExcel(List<T> data, HttpServletResponse response, Class<? extends T> clazz, String fileName) {
        HSSFWorkbook workbook = null;
        SimpleDateFormat dateFormat;
        if (!ObjectUtils.isEmpty(data) && !CollectionUtils.isEmpty(data)) {
            logger.info("开始导出excel,数据量：{}", data.size());
            workbook = new HSSFWorkbook();
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Field> fields = new ArrayList<>();
            //获取所有需要到处的列
            Field[] fieldList = clazz.getDeclaredFields();
            for (Field field : fieldList) {
                if (field.isAnnotationPresent(Excel.class)) {
                    fields.add(field);
                }
            }

            double sheetNo = Math.ceil(data.size() / SHEET_SIZE);
            for (int index = 0; index <= sheetNo; index++) {
                HSSFSheet sheet = workbook.createSheet();
                workbook.setSheetName(index, SHEET_NAME + index);
                HSSFRow row;
                HSSFCell cell;

                row = sheet.createRow(0);
                // 写入各个字段的列头名称
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
                    cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
                    // 设置列宽
                    sheet.setColumnWidth(n, WIDTH);
                    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    cellStyle.setWrapText(true);
                    cell.setCellStyle(cellStyle);
                    // 写入列名
                    cell.setCellValue(attr.name());
                }

                //开始写入数据
                int startNo = index * SHEET_SIZE;
                int endNo = Math.min(startNo + SHEET_SIZE, data.size());
                // 写入各条记录,每条记录对应excel表中的一行
                HSSFCellStyle cs = workbook.createCellStyle();
                cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                for (int i = startNo; i < endNo; i++) {
                    row = sheet.createRow(i + 1 - startNo);
                    T vo = data.get(i);
                    for (int j = 0; j < fields.size(); j++) {
                        // 获得field.
                        Field interField = fields.get(j);
                        // 设置实体类私有属性可访问
                        interField.setAccessible(true);
                        try {
                            // 创建cell
                            cell = row.createCell(j);
                            cell.setCellStyle(cs);
                            Class<?> classType = interField.getType();
                            try {
                                if (classType == Date.class) {
                                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                    cell.setCellValue(dateFormat.format(interField.get(vo)));
                                } else if (classType == String.class) {
                                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                    cell.setCellValue(String.valueOf(interField.get(vo)));
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
            return;
        }
        write(workbook, response, fileName);
    }

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