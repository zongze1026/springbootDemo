package com.zongze.util;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Create By xzz on 2019/3/22
 */
public class ObjectUtil {


    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    private ObjectUtil() {
    }

    public static <T> T copyProperties(Object source, Class<? extends T> targetClass) {
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Method getMethod(Class oClass, String methodName) {
        List<Method> methods = getMethods(oClass);
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }


    public static final Field getField(Class oClass, String fieldName) {
        List<Field> fields = getFields(oClass);
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }


    public static final List<Method> getMethods(Class oClass) {
        List<Method> methods = new ArrayList<>();
        Class tempClass = oClass;
        while (tempClass != null && !Object.class.getName().equals(tempClass.getName())) {
            methods.addAll(Arrays.asList(tempClass.getDeclaredMethods()));
            tempClass = tempClass.getSuperclass();
        }
        return methods;
    }


    public static final List<Field> getFields(Class oClass) {
        List<Field> fields = new ArrayList<>();
        Class tempClass = oClass;
        while (tempClass != null && !Object.class.getName().equals(tempClass.getName())) {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }
        return fields;
    }


    /**
     * target 目标对象
     * methodName 目标方法名称
     * clazz 返回指类型
     * args 参数
     */
    public static final <T> T invokeMethod(Object target, String methodName, Class<T> clazz, Object... args) {
        try {
            Method method = getMethod(target.getClass(), methodName);
            method.setAccessible(true);
            return (T) method.invoke(target, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * target 目标对象
     * fieldName 属性名称
     * clazz 返回指类型
     */
    public static final <T> T invokeField(Object target, String fieldName, Class<T> clazz) {
        try {
            Field field = getField(target.getClass(), fieldName);
            field.setAccessible(true);
            return (T) field.get(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static JSONObject parseXml(Document document) {
        JSONObject jsonObject = new JSONObject();
        Element root = document.getRootElement();
        return eleToJson(root, jsonObject);
    }


    private static JSONObject eleToJson(Element document, JSONObject jsonObject) {
        Iterator<Element> iterator = document.elementIterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            if (element.elements().size() > 0) {
                eleToJson(element, jsonObject);
            } else {
                jsonObject.put(element.getName().toLowerCase().replaceAll("_", ""), element.getData());
            }
        }
        return jsonObject;
    }


    /**
     * xml转成jsonObject
     */
    public static JSONObject convertXmlStr(String xmlStr) {
        try {
            Document document = DocumentHelper.parseText(xmlStr);
            return parseXml(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String xmlString = "<?xml version = \"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<DOCUMENT>\n" +
                "\t<RETURN_CODE>000000</RETURN_CODE>\n" +
                "    <RETURN_MSG></RETURN_MSG>\n" +
                "\t<CURPAGE>1</CURPAGE>\n" +
                "\t<PAGECOUNT>1</PAGECOUNT>\n" +
                "\t<TOTAL>1</TOTAL>\n" +
                "\t<PAYAMOUNT>0.00</PAYAMOUNT>\n" +
                "\t<REFUNDAMOUNT>0.00</REFUNDAMOUNT>\n" +
                "\n" +
                "\t<QUERYORDER>\n" +
                "\t\t<MERCHANTID>105335000000965</MERCHANTID>\n" +
                "\t\t<BRANCHID>330000000</BRANCHID>\n" +
                "\t\t<POSID>006080355</POSID>\n" +
                "\t\t<ORDERID>590125157926830080</ORDERID>\n" +
                "\t\t<ORDERDATE>20190617102737</ORDERDATE>\n" +
                "\t\t<ACCDATE>20190617</ACCDATE>\n" +
                "\t\t<AMOUNT>0.03</AMOUNT>\n" +
                "\t\t<STATUSCODE>1</STATUSCODE>\n" +
                "\t\t<STATUS>成功</STATUS>\n" +
                "\t\t\n" +
                "\t\t<REFUND>0.00</REFUND>\n" +
                "\t\t<SIGN>4d062c7fc04001ed81c1ca1f48eee5459b157a683e25be98f41a844622517c189802f39d1bea737b8270be0db0b0aaff59d337112ec808671391a1388a51abfcb23c4129499ab07afe66ba7541c484b5566532f5ba62de8b88df41362fca37e14c1a9f1837b80718e6fb24c58fc7661741d12c5e7ca2c15086eeea9734000172</SIGN>\n" +
                "\t</QUERYORDER>\n" +
                "\n" +
                "</DOCUMENT>";
        JSONObject jsonObject = ObjectUtil.convertXmlStr(xmlString);
        System.out.println(jsonObject.toJSONString());
    }


}
