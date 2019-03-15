package com.zongze.entity;
import org.dom4j.DocumentException;
import java.io.Serializable;
import java.util.*;

/**
 * Create By xzz on 2018/11/26
 */
public class User extends AbstractIntity {

    private Integer id;

    private String userName;

    private String passWord;

    private Integer age;

    private TestEnum tenum;


    public TestEnum getTenum() {
        return tenum;
    }

    public void setTenum(TestEnum tenum) {
        this.tenum = tenum;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    static class UserBuilder {
        Long id;

        String userName;

        String passWord;

        Integer age;

        public UserBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder setPassWord(String passWord) {
            this.passWord = passWord;
            return this;
        }

        public UserBuilder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public User builder() {
            return new User(this);
        }
    }


    public User(UserBuilder builder) {
        this.userName = builder.userName;
        this.age = builder.age;
        this.passWord = builder.passWord;
    }

    public User() {
        System.out.println("user被实例化");
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
