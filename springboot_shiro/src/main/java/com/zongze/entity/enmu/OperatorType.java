package com.zongze.entity.enmu;

import java.io.Serializable;


/**
 * Create By xzz on 2019/3/26
 */
public enum OperatorType implements Serializable {

    LOG_ADD("新增", 0),
    LOG_UPDATE("修改", 1),
    LOG_DELETE("修改", 1),
    LOG_FORBID("禁用", 1),
    LOG_ALLOW("启用", 1),
    ;

    OperatorType(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }

    private String desc;
    private Integer code;

    public static OperatorType getType(Integer code) {
        for (OperatorType operatorType : OperatorType.values()) {
            if (code == operatorType.code) {
                return operatorType;
            }
        }
        return null;
    }


}
