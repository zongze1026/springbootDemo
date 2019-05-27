package com.zongze.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Create By xzz on 2019/5/27
 */
@Data
public class TestModel {

    @NotBlank(message = "名称不能为空！")
    private String name;

    @NotBlank(message = "昵称不能为空！")
    private String nickName;



}
