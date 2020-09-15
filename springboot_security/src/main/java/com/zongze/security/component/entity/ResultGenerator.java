package com.zongze.security.component.entity;

import java.util.List;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    public static BaseResult genSuccessBaseResult(Object o) {
        BaseResult r = new BaseResult();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMsg());
        if (o instanceof List) {
            List list = (List) o;
            r.setCount(list.size());
        }
        r.setData(o);
        return r;
    }

    public static BaseResult genFailBaseResult() {
        BaseResult r = new BaseResult();
        r.setCode(ResultCode.FAIL.getCode());
        r.setMessage(ResultCode.FAIL.getMsg());
        return r;
    }

    public static BaseResult genFailBaseResultByMsg(String msg) {
        BaseResult r = new BaseResult();
        r.setCode(ResultCode.FAIL.getCode());
        r.setMessage(msg);
        return r;
    }

    public static BaseResult genFailBaseResultByRsultCode(ResultCode resultCode) {
        BaseResult r = new BaseResult();
        r.setCode(resultCode.getCode());
        r.setMessage(resultCode.getMsg());
        return r;
    }

    public static BaseResult genSuccessBaseResultByMsg(String msg) {
        BaseResult r = new BaseResult();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(msg);
        return r;
    }

    public static BaseResult genResult(String code, String message, Object object) {
        BaseResult r = new BaseResult();
        r.setCode(code);
        r.setMessage(message);
        r.setData(object);
        return r;
    }

    public static BaseResult genResults(Object object,int count) {
        BaseResult r = new BaseResult();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMsg());
        r.setData(object);
        r.setCount(count);
        return r;
    }
}
