package com.zongze.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zongze.valid.Group1;
import com.zongze.valid.Group2;
import jdk.nashorn.internal.ir.CatchNode;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Create By xzz on 2018/12/13
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User  implements Serializable {

    @NotBlank(groups = {Group1.class}, message = "用户名不能为空！")
    private String userName;

    @NotNull(groups = {Group1.class}, message = "用户名不能为空！")
    private Long age;

    @NotBlank(groups = {Group2.class}, message = "昵称不能为空！")
    private String nickName;

    @JsonIgnore
    private transient String passWord;

    public User(String userName, Long age,
                String nickName,
                String passWord) {
        this.userName = userName;
        this.age = age;
        this.nickName = nickName;
        this.passWord = passWord;
    }

    public User() {
    }

    private User(String userName) {
        this.userName = userName;
    }

    public String eat(){
        return "一次一大碗";
    }



}
