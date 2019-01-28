package com.zongze.entity;

import com.zongze.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Create By xzz on 2019/1/14
 */
public class ExcelTest implements Serializable {

    private Integer id;

    private String name;

    private Double money;

    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getBirthday() {
        if(this.birthday == null){
            return null;
        }
        return DateUtil.format(this.birthday);
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public static void main(String[] args) {
//        double m = 1.1;
//        double n = 1.1;
//        BigDecimal multiply1 = new BigDecimal(Double.toString(m)).multiply(new BigDecimal(Double.toString(n)));
//        System.out.println(multiply1.doubleValue());
//
//        BigDecimal multiply = new BigDecimal(m).multiply(new BigDecimal(n));
//        System.out.println(multiply.doubleValue());

        double m1 = 2.4;
        double n1 = 1.1;
        BigDecimal multiply2 = new BigDecimal(Double.toString(m1)).divide(new BigDecimal(Double.toString(n1)));
        System.out.println(multiply2.doubleValue());
        System.out.println(m1/n1);


    }



}