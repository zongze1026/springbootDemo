package com.zongze.entity;

public enum ExceptionEnum {
    SUCCESS("C_000", "成功"),
    SYSTEM_ERROR("C_500", "您的请求存在异常,请修改后再进行尝试!"),
    ERR_LOGIN("C_001","用户名或密码输入有误，请检查后重新输入!"),
    RE_LOGIN("C_002", "登录信息失效，请重新登录!"),
    SAVE_ERROR("C_004","数据添加异常!"),
    OPEN_ERROR("C_006","请先添加合作商费率!"),
    NO_ENOUGH("C_007","余额不足"),
    ERROR_CODE("C_008","验证码错误！！"),
    ERROR_SELECT("C_009","数据查询异常！！"),
    RECHARGE_TIMEOUT("C_016","充值时间超时，请重新尝试！！"),
    NO_DF_POWER("C_015","没有开通代付功能！！"),
    SEND_ERROR("C_017","验证码发送异常！！"),
    CHANNELCODE_EXIST("C_018","添加失败，channelCode重复！"),

    OTHER("C_010","未知错误"),
    ERR_PARTNER("C_011","合作商信息有误!"),
    NO_POWER("C_012","无此权限!"),
    ERR_REQ("C_013","参数有误!"),
    ILLEGAL_REQ("C_014","非法请求！"),
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
