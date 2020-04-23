package com.zongze.netty.protobuf;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;

/**
 * Create By xzz on 2020/4/22
 * <p>
 * protoc执行命令：protoc -I=[proto文件的目录] --java_out=[生成的java文件存放的目录] [需要编译的proto文件]
 * protoc -I=src/main/java/netty/protobuf --java_out=src/main/java Student.proto
 * 1.如果.proto文件和protoc.exe在同一个文件夹下，-I参数可以不指定
 * 2.如果需要将生成的java文件和protoc.exe放在同一个目录下的话: --java_out=.
 */
public class ProtoBufTest {

    public static void main(String[] args) throws IOException {

        Student student = protoBufTest();
        //java序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(bos);
        outputStream.writeObject(student);
        byte[] bytes = bos.toByteArray();
        System.out.println(bytes.length);  //输出458，和protoBuf的79相比还是有点大
        System.out.println(JSON.toJSONString(student).getBytes().length);  //json序列化161
    }

    /**
     * protubuf序列化和反序列化测试，并将数据设置到Student中去测试java序列化
     */
    private static Student protoBufTest() throws InvalidProtocolBufferException {
        StudentEntity.Student student = StudentEntity.Student.newBuilder().setAge(20).setUserName("小可爱")
                .addBooks(StudentEntity.Book.newBuilder().setBookName("三国演义").build())
                .addBooks(StudentEntity.Book.newBuilder().setBookName("水浒传").build())
                .addBooks(StudentEntity.Book.newBuilder().setBookName("红楼梦").build())
                .addFriends("小明").addFriends("小李").addFriends("小王").build();

        Student student2 = new Student();
        //模拟序列化
        byte[] bytes = student.toByteArray();
        System.out.println(bytes.length); //79
        //假设拿到byte数组进行反序列化
        StudentEntity.Student student1 = StudentEntity.Student.parseFrom(bytes);

        student2.setAge(student1.getAge());
        student2.setUserName(student1.getUserName());
        System.out.println("年龄=" + student1.getAge() + "\n" + "姓名=" + student1.getUserName());
        Iterator<String> iterator = student1.getFriendsList().iterator();
        while (iterator.hasNext()) {
            String friendsName = iterator.next();
            System.out.println(friendsName);
            student2.getFirdents().add(friendsName);
        }
        Iterator<StudentEntity.Book> bookIterator = student1.getBooksList().iterator();
        while (bookIterator.hasNext()) {
            String bookName = bookIterator.next().getBookName();
            System.out.println(bookName);
            student2.getBooks().add(new Book(bookName));
        }
        return student2;
    }


}
