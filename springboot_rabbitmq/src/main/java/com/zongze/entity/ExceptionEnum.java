package com.zongze.entity;

public enum ExceptionEnum {
    SUCCESS("C_000", "成功"),
    ERR_REQ("C_001", "参数错误!"),


    SYSTEM_ERROR("C_500", "系统异常!"),
    ;

    /**
     * 构造方法
     *
     * @param code
     * @param message
     */
    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * code
     */
    private String code;

    /**
     * 结果消息
     */
    private String message;

    /**
     * 返回 code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 返回 message
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 通过code获取对应消息
     *
     * @param code
     * @return message
     */
    public static String getMessage(String code) {
        if (code == null) {
            return null;
        }
        for (ExceptionEnum s : ExceptionEnum.values()) {
            if (s.code.equals(code)) {
                return s.message;
            }
        }
        return null;
    }
}
