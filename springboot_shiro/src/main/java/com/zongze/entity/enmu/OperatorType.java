package com.zongze.entity.enmu;

import lombok.Getter;
import java.io.Serializable;


/**
 * Create By xzz on 2019/3/26
 */
@Getter
public enum OperatorType implements Serializable {

    LOG_ADD("新增", 0),
    LOG_UPDATE("修改", 1),
    LOG_DELETE("修改", 2),
    LOG_FORBID("禁用", 3),
    LOG_ALLOW("启用", 4),
    ;

    OperatorType(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    private String desc;
    private Integer code;

    public static OperatorType getType(Integer code) {
        for (OperatorType operatorType : OperatorType.values()) {
            if (code == operatorType.getCode()) {
                return operatorType;
            }
        }
        return null;
    }


}
