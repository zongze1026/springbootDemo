package com.zongze.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.*;

public class ObjectUtil {


    /**
     * 将下划线转成驼峰式
     * @param param
     * @return
     */
    public static String underlineToCamel(String param){
        int index;
        do {
            index = param.indexOf("_");
            if(index != -1){
                param = param.substring(0, index) + param.substring(index + 1, index + 2).toUpperCase() + param.substring(index + 2);
            }
        }while (index != -1);
        return param;
    }

    public static <T> T underlineToCamel(Object source, Class<T> targetClass){
        if(source == null || targetClass == null){
            return null;
        }
        Map map = objectToMap(source);
        Map<String, Object> newMap = new HashMap<>();
        for(Object key: map.keySet()){
            String newKey = underlineToCamel((String)key);
            newMap.put(newKey, map.get(key));
        }
        return mapToObject(newMap, targetClass);
    }

    public static <T> T underlineToCamel(Object source, T target){
        Object target2 = underlineToCamel(source, target.getClass());
        BeanUtils.copyProperties(target2, target);
        return target;
    }

    public static <T> List<T> underlineToCamel(List list, Class<T> targetClass){
        if(null == list || list.size() < 1){
            return list;
        }
        for(int i=0; i<list.size(); i++){
            list.set(i, underlineToCamel(list.get(i), targetClass));
        }
        return list;
    }


    /**
     * 驼峰转下划线
     * @param params
     * @return
     */
    public static String camelToUnderline(String params){
        return params.replaceAll("([A-Z])", "_$1").toLowerCase();
    }

    public static <T> T camelToUnderline(Object source, Class<T> targetClass){
        if(source == null || targetClass == null){
            return null;
        }
        Map map = objectToMap(source);
        Map<String, Object> newMap = new HashMap<>();
        for(Object key: map.keySet()){
            String newKey = camelToUnderline((String)key);
            newMap.put(newKey, map.get(key));
        }
        return mapToObject(newMap, targetClass);
    }


    /**
     * 对象转换
     * @param source
     * @param targetClass
     * @return
     */
    public static <T> T convert(Object source, Class<T> targetClass){
        T target = null;
        try{
            target = mapToObject(objectToMap(source), targetClass);
        }catch (Exception e){
            e.printStackTrace();
            try { // 尝试bean拷贝的方式（向下兼容）
                target = targetClass.newInstance();
                BeanUtils.copyProperties(source, target);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return target;
    }

    public static <T> T convert(Object source, T target){
        try{
            target = mapToObject(objectToMap(source), target);
        }catch (Exception e){
            e.printStackTrace();
            try { // 尝试bean拷贝的方式（向下兼容）
                BeanUtils.copyProperties(source, target);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return target;
    }

    public static Document convertToXml(Object object){
        Map<String, Object> map = objectToMap(object);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        for(String key: map.keySet()){
            Object value = map.get(key);
            Element element = root.addElement(key);
            element.addCDATA(value + "");
        }
        return document;
    }

    public static <T> T convertXml(Document document, Class<T> targetClass){
        Element root = document.getRootElement();
        Map<String, Object> map = new HashMap<>();
        for(Iterator<Element> it = root.elementIterator(); it.hasNext();){
            Element element = it.next();
            map.put(element.getName(), element.getData());
        }
        return ObjectUtil.convert(map, targetClass);
    }

    public static <T> T convertXmlStr(String xmlStr, Class<T> targetClass){
        T object = null;
        try {
            Document document = DocumentHelper.parseText(xmlStr);
            object = convertXml(document, targetClass);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static <T> T mapToObject(Map<String, Object> map, T obj) {

        try {
            for (Field field : getFields(obj.getClass())) {
                int mod = field.getModifiers();
                if(Modifier.isStatic(mod) || Modifier.isFinal(mod) || !map.containsKey(field.getName())){
                    continue;
                }
                field.setAccessible(true);
                Object value = map.get(field.getName());
                if(field.getType().isEnum() && value instanceof String){ // 枚举类型
                    Method method = field.getType().getMethod("valueOf", String.class);
                    Object result = method.invoke(field.getType(), value);
                    field.set(obj, result);
                }else{
                    field.set(obj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
        if(beanClass.isAssignableFrom(Map.class)){
            return (T)map;
        }
        T obj = null;
        try {
            obj = beanClass.newInstance();
            for (Field field : getFields(obj.getClass())) {
                int mod = field.getModifiers();
                if(Modifier.isStatic(mod) || Modifier.isFinal(mod) || !map.containsKey(field.getName())){
                    continue;
                }
                field.setAccessible(true);
                Object value = map.get(field.getName());
                if(field.getType().isEnum() && value instanceof String){ // 枚举类型
                    Method method = field.getType().getMethod("valueOf", String.class);
                    Object result = method.invoke(field.getType(), value);
                    field.set(obj, result);
                }else{
                    field.set(obj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj){
        if(obj instanceof Map){
            return (Map)obj;
        }

        Map<String, Object> map = new HashMap<>();
        if(obj == null){
            return map;
        }

        for (Field field : getFields(obj.getClass())) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    public static String objectToFormStr(Object object){
        Map<String, Object> map = objectToMap(object);
        StringBuffer stringBuffer = new StringBuffer();
        for(String key: map.keySet()){
            try {
                stringBuffer.append(key + "=" + URLEncoder.encode(map.get(key).toString(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            stringBuffer.append("&");
        }
        if(stringBuffer.length() > 0){
            stringBuffer.setLength(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static final List<Field> getFields(Class oClass){
        List<Field> fields = new ArrayList<>();
        Class tempClass = oClass;
        while (tempClass != null && !"java.lang.object".equals(tempClass.getName().toLowerCase())){
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }
        return fields;
    }

    public static final Field getField(Class oClass, String fieldName){
        List<Field> fields = getFields(oClass);
        for(Field field: fields){
            if(field.getName().equals(fieldName)){
                return field;
            }
        }
        return null;
    }

    public static final List<Method> getMethods(Class oClass){
        List<Method> methods = new ArrayList<>();
        Class tempClass = oClass;
        while (tempClass != null && !"java.lang.object".equals(tempClass.getName().toLowerCase())){
            methods.addAll(Arrays.asList(tempClass.getDeclaredMethods()));
            tempClass = tempClass.getSuperclass();
        }
        return methods;
    }

    public static final Method getMethod(Class oClass, String methodName){
        List<Method> methods = getMethods(oClass);
        for(Method method: methods){
            if(method.getName().equals(methodName)){
                return method;
            }
        }
        return null;
    }
}
