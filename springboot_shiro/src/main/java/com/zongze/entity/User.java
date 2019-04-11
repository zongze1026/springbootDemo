package com.zongze.entity;
import com.zongze.annotation.Phone;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


/**
 * Create By xzz on 2018/11/26
 */
@Setter
@Getter
public class User extends AbstractEntity {

    private Integer id;

    private String userName;

    private String passWord;

    private Integer age;

    @Phone
    private String phone;


    public String getUserName() {
        return userName;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User() {
    }

}
