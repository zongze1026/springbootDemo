package com.zongze.test;
import com.zongze.entity.ExcelTest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By xzz on 2019/1/14
 */
public class FieldTest {


    public static void main(String[] args) throws Exception {
        ExcelTest excelTest = new ExcelTest();
        excelTest.setBirthday(new Date());
        System.out.println(excelTest.getBirthday());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Class<ExcelTest> aClass = ExcelTest.class;
        Field field = aClass.getDeclaredField("birthday");
        if(field.getType() == Date.class){
            field.setAccessible(true);
            String birthday = format.format((Date)field.get(excelTest));
            System.out.println(birthday);
        }
    }



}
