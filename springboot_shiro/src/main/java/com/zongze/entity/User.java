package com.zongze.entity;
import com.zongze.validator.Phone;
import lombok.Getter;
import lombok.Setter;
import org.dom4j.DocumentException;
import java.util.*;

/**
 * Create By xzz on 2018/11/26
 */
@Setter
@Getter
public class User extends AbstractIntity {

    private Integer id;

    private String userName;

    private String passWord;

    private Integer age;

    @Phone(allowEmpty = true)
    private String phone;



    public User(String userName) {
        this.userName = userName;
    }

    public User() {
    }

    public static void main(String[] args) throws DocumentException {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)-1);
//        calendar.set(Calendar.HOUR_OF_DAY,0);
//        calendar.set(Calendar.MINUTE,0);
//        calendar.set(Calendar.SECOND,0);
//        calendar.set(Calendar.MILLISECOND,0);
//        calendar.setTimeInMillis(calendar.getTimeInMillis()-1);
//        System.out.println(JSON.toJSONString(DateUtil.format(calendar.getTime())));
//        calendar.set(Calendar.WEEK_OF_YEAR,calendar.get(Calendar.WEEK_OF_YEAR)-1);
//        System.out.println(JSON.toJSONString(DateUtil.format(calendar.getTime())));
//
//        int year = calendar.get(Calendar.YEAR);
//        int week = calendar.get(Calendar.WEEK_OF_YEAR);
//        calendar.set(Calendar.YEAR,year);
//        calendar.set(Calendar.WEEK_OF_YEAR,week);
//        calendar.set(Calendar.DAY_OF_WEEK,2);
//        System.out.println(DateUtil.format(calendar.getTime()));

//        Document document = DocumentHelper.createDocument();
//        Map<String,String>data = new HashMap<>();
//        data.put("userName","test");
//        data.put("age","14");
//        Element xml = document.addElement("xml");
//        for (Map.Entry<String,String> entry:data.entrySet()){
//            Element element = xml.addElement(entry.getKey());
//            element.addCDATA(entry.getValue());
//        }
//        String s = document.asXML();
//        System.out.println(s);
//        Document doc = DocumentHelper.parseText(s);
//        Element rootElement = doc.getRootElement();
//        Iterator<Element> iterator = rootElement.elementIterator();
//        Map<String,Object> result = new HashMap<>();
//        while (iterator.hasNext()){
//            Element next = iterator.next();
//            result.put(next.getName(),next.getStringValue());
//            System.out.println(next.getName());
//            System.out.println(next.getStringValue());
//        }
//        Calendar instance = Calendar.getInstance();
//        instance.set(Calendar.HOUR_OF_DAY,instance.get(Calendar.HOUR_OF_DAY)+1);
//        System.out.println(DateUtil.format(instance.getTime()));
//        System.out.println("abc".equalsIgnoreCase(null));

        Map<String, String> signMap = new TreeMap<>();
//abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ

        signMap.put("orderNo", "2532145241");
        signMap.put("price", "5324.02");
        signMap.put("user", "王老板");
        signMap.put("abc", "456");
        signMap.put("aaa", "abc");
        for (Map.Entry<String, String> entry : signMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }


    }

}
