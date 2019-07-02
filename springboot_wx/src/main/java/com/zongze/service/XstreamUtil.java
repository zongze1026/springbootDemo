package com.zongze.service;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.zongze.entity.Dog;
import com.zongze.entity.User;

import java.util.Date;

/**
 * Create By xzz on 2019/7/2
 */
public class XstreamUtil {

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


    public static void main(String[] args) {
        User user = new User();
        Dog dog = new Dog();
        dog.setDAge(28);
        dog.setDName("二哈");
        user.setAge(18);
        user.setName("站三");
        user.setBirthday(new Date());
        user.setDog(dog);
        String toxml = XstreamUtil.toxml(user);
        System.out.println(toxml);

        String xmlstring = "<xml>\n" +
                "  <name>站三</name>\n" +
                "  <age>18</age>\n" +
                "  <birthday>2019-07-02 09:12:36.794 UTC</birthday>\n" +
                "  <dog>\n" +
                "    <dName>二哈</dName>\n" +
                "    <dAge>28</dAge>\n" +
                "  </dog>\n" +
                "</xml>";

        Object object = XstreamUtil.convertObject(xmlstring);
        System.out.println(JSON.toJSONString(object));

    }


}
