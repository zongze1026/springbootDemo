package com.zongze.bigdata.byteArrayOutPutStream;

import com.alibaba.fastjson.JSON;
import com.zongze.model.User;

import java.io.*;

/**
 * Create By xzz on 2019/5/29
 * 使用byteArrayOutPutStream复制对象
 */
public class DeepCopy {


    public static void main(String[] args) {

        try {
            User user = new User("张三", 15l, "大黄", "123456");
            System.out.println(user.getPassWord());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(user);

            byte[] bytes = baos.toByteArray();

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            User user1 = (User) ois.readObject();
            System.out.println(user1.getPassWord());
            System.out.println(user == user1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }




}
