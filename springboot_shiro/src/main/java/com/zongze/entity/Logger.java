package com.zongze.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * Create By xzz on 2019/3/26
 */
@Setter
@Getter
public class Logger extends AbstractEntity{

    @ApiModelProperty("日志id")
    private Long id;
    @ApiModelProperty("日志模块")
    private String title;
    @ApiModelProperty("请求地址")
    private String uri;
    @ApiModelProperty("日志内容")
    private String content;
    @ApiModelProperty("请求参数")
    private String reqParam;
    @ApiModelProperty("响应结果")
    private String respResult;
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("操作者")
    private String operator;
    @ApiModelProperty("ip地址")
    private String ip;

}
