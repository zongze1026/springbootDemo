package com.zongze.entity;
import com.alibaba.fastjson.JSON;
import java.io.Serializable;

/**
 * Create By xzz on 2018/11/26
 */
public class User implements Serializable {

    private Long id;

    private String userName;

    private String passWord;

    private Integer age;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

     static class UserBuilder{
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

         public User builder(){
             return new User(this);
         }
     }


    public User(UserBuilder builder) {
        this.id=builder.id;
        this.userName=builder.userName;
        this.age=builder.age;
        this.passWord=builder.passWord;
    }

    public User(){}

    public static void main(String[] args) {
      User a = new User.UserBuilder().setUserName("张三").setAge(18).builder();
      User b = new User.UserBuilder().setUserName("李四").setAge(38).setId(1002l).setPassWord("52142").builder();
        System.out.println(JSON.toJSONString(a));
        System.out.println(JSON.toJSONString(b));
    }

}
