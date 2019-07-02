package com.zongze.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Date;

/**
 * Create By xzz on 2019/7/2
 */
@Data
@XStreamAlias("xml")
public class User {

    private String name;

    private Integer age;

    private Date birthday;

    private Dog dog;

}
