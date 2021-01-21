package com.zongze.design;
import com.alibaba.fastjson.JSON;
import com.zongze.util.DateUtil;
import java.util.Date;

/**
 * Create By xzz on 2019/1/23
 * 建造者模式：需要一个私有静态的内部类，内部类的属性保持和外部类一致，可以通过内部类来初始化属性
 * 外部类提供入参为内部类对象的构造；这样就可以达到构造一个任意属性的对象
 */
public class Builder {

    private String userName;

    private Integer age;

    private Date birthday;

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

    public String getBirthday() {
        if(this.birthday == null){
            return null;
        }
        return DateUtil.formatDate(birthday);
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 提供属性相同的私有静态的内部类
     */
    private static class BuilderInstance {
        private String userName;

        private Integer age;

        private Date birthday;

        /**
         * 每个属性提供一个set方法;并返回当前对象
         */
        public BuilderInstance setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public BuilderInstance setAge(Integer age) {
            this.age = age;
            return this;
        }

        public BuilderInstance setBirthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        /**
         * builder方法就是执行外部的构造方法
         */
        private Builder build() {
            return new Builder(this);
        }

    }

    /**
     * 提供入参是内部类对象的构造
     */
    public Builder(BuilderInstance builder) {
        this.userName = builder.userName;
        this.age = builder.age;
        this.birthday = builder.birthday;
    }


    public static void main(String[] args) {
        Builder builder = new BuilderInstance().setUserName("张三").setAge(18).build();
        Builder builder2 = new BuilderInstance().setUserName("李四").setAge(38).setBirthday(new Date()).build();
        System.out.println(JSON.toJSONString(builder));
        System.out.println(JSON.toJSONString(builder2));
    }


}
