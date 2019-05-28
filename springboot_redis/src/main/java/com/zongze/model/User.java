package com.zongze.model;

import com.zongze.valid.Group1;
import com.zongze.valid.Group2;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Create By xzz on 2018/12/13
 */
@Data
public class User implements Serializable {

    @NotBlank(groups = {Group1.class}, message = "用户名不能为空！")
    private String userName;

    @NotNull(groups = {Group1.class}, message = "用户名不能为空！")
    private Long age;

    @NotBlank(groups = {Group2.class},message = "昵称不能为空！")
    private String nickName;

    private String passWord;




}
