package com.zongze.entity;

public class ResultResp {
    /**
     * code
     */
    private String code = ExceptionEnum.SUCCESS.getCode();
    /**
     * 消息
     */
    private String msg = ExceptionEnum.SUCCESS.getMessage();
    /**
     * 具体请求结果
     */
    private Object data;

    /**
     * 构造方法
     */
    public ResultResp() {
    }

    public ResultResp(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultResp succ() {
        return succ(null);
    }

    public static ResultResp succ(Object data) {
        ResultResp ret = new ResultResp();
        ret.setCode(ExceptionEnum.SUCCESS.getCode());
        ret.setMsg(ExceptionEnum.SUCCESS.getMessage());
        ret.setData(data);
        return ret;
    }

    public static ResultResp succ(Object data, ExceptionEnum m) {
        ResultResp ret = new ResultResp();
        ret.setCode(m.getCode());
        ret.setMsg(m.getMessage());
        ret.setData(data);
        return ret;
    }


    public static ResultResp fail(ExceptionEnum m) {
        ResultResp resp = new ResultResp();
        resp.setCode(m.getCode());
        resp.setMsg(m.getMessage());
        return resp;
    }


    public static ResultResp fail(String msg) {
        ResultResp resp = new ResultResp();
        resp.setCode(ExceptionEnum.ERR_REQ.getCode());
        resp.setMsg(msg);
        return resp;
    }

}
