package com.zongze.bigdata.io;
import com.zongze.model.User;
import java.io.*;

/**
 * Create By xzz on 2019/5/29
 * 使用byteArrayOutPutStream复制对象
 */
public class byteArrayStream {


    public static void main(String[] args) {

//        test();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write("hellow".getBytes("utf-8"));
            System.out.println(out.toByteArray().length);
            out.reset();
            System.out.println(out.toByteArray().length);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void test() {
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
