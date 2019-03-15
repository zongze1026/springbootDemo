package com.zongze.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Create By xzz on 2019/3/15
 */
@Setter
@Getter
public class AbstractIntity implements Serializable {

    private Integer pageNum;

    private Integer pageSize;

    private Date start;

    private Date end;

}
