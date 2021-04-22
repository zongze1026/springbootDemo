package com.zongze.util;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

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


    public static <T> T copyProperties(Class<? extends T> targetClass, Object source) {
        try {
            Class<?> sourceClass = source.getClass();
            T t = targetClass.newInstance();
            List<Field> targetFields = getFields(targetClass);
            List<Field> sourceFields = getFields(sourceClass);
            for (Field sourceField : sourceFields) {
                for (Field targetField : targetFields) {
                    if (sourceField.getName().equals(targetField.getName())) {
                        PropertyDescriptor sourceDescriptor = new PropertyDescriptor(sourceField.getName(), sourceClass);
                        PropertyDescriptor targetDescriptor = new PropertyDescriptor(sourceField.getName(), targetClass);
                        Method readMethod = sourceDescriptor.getReadMethod();
                        Method writeMethod = targetDescriptor.getWriteMethod();
                        writeMethod.invoke(t, readMethod.invoke(source, null));
                    }
                }
            }
            return t;
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
        return xmlStringToJson(root, jsonObject);
    }


    private static JSONObject xmlStringToJson(Element document, JSONObject jsonObject) {
        Iterator<Element> iterator = document.elementIterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            if (element.elements().size() > 0) {
                xmlStringToJson(element, jsonObject);
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


    public static String toxml(Object object) {
        XStream xStream = new XStream();
        xStream.processAnnotations(object.getClass());
        String s = xStream.toXML(object);
        return s;
    }


    public static Object convertObject(String xml) {
        XStream xStream = new XStream();
        return xStream.fromXML(xml);
    }


    public static byte[] int2bytes(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i >> 24);
        bytes[1] = (byte) (i >> 16);
        bytes[2] = (byte) (i >> 8);
        bytes[3] = (byte) (i >> 0);
        return bytes;
    }

    public static byte[] short2bytes(short i) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (i >> 8);
        bytes[1] = (byte) i;
        return bytes;
    }


    public static int bytes2int(byte[] bytes) {
        return (bytes[0] << 24) | ((bytes[1] & 0xff) << 16) | ((bytes[2] & 0xff) << 8) | (bytes[3] & 0xff);
    }


    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexString(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }


    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] hexStringToBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }


    public static void main(String[] args) {
        byte[] bytes = int2bytes(15);
        System.out.println(bytesToHexString(bytes));
        int i = Integer.parseUnsignedInt("0000000f", 16);
        System.out.println(i);
    }


}
