package com.zongze.entity;
import com.alibaba.fastjson.JSON;
import com.zongze.util.DateUtil;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Create By xzz on 2018/11/26
 */
public class User implements Serializable {

    private Long id;

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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)-1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.setTimeInMillis(calendar.getTimeInMillis()-1);
        System.out.println(JSON.toJSONString(DateUtil.format(calendar.getTime())));
        calendar.set(Calendar.WEEK_OF_YEAR,calendar.get(Calendar.WEEK_OF_YEAR)-1);
        System.out.println(JSON.toJSONString(DateUtil.format(calendar.getTime())));

        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.WEEK_OF_YEAR,week);
        calendar.set(Calendar.DAY_OF_WEEK,2);
        System.out.println(DateUtil.format(calendar.getTime()));

    }

}
