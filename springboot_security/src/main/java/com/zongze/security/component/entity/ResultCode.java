package com.zongze.security.component.entity;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS("0000", "成功"),
    RPCSUCCESS("200", "rpcsuccess"),
    /**
     * 失败
     */
    FAIL("1111", "失败"),
    INNER_FAIL("1112", "内部错误请联系管理员"),


    PARAMERROR("10070","参数错误"),
    /**
     * 接口不存在
     */
    NOT_FOUND("404", "服务不存在"),
    INTERNAL_SERVER_ERROR("500","服务器异常"),

    USERLOCK("10090", "账户被锁定"),
    USERPWDNOTFOUND("10092", "原密码不对"),
    LOGINERROR("10093", "账户名或密码不对！"),
    LOGINAUTHENTERROR("10094", "未登录！"),
    AUTHENTERROR("10095","该用户无权限"),
    /**
     * 服务器内部错误
     */
    SERVER_ERROR("10001"),
    USERNOTFOUND("10091", "该用户未发现"),
    ORDERNOTFOUND("10047","该用户订单未发现"),
    CANNOT_FIND_OPENID("30001","openId未找到");
    private String code;
    private String msg;

    ResultCode(String code) {
        this.code = code;
    }

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
