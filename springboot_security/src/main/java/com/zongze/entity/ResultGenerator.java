package com.zongze.entity;


public class ResultGenerator {

    public static BaseResult genSuccessBaseResult(Object o) {
        BaseResult r = new BaseResult();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMsg());
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

    public static BaseResult genResult(String code, String message, Object object) {
        BaseResult r = new BaseResult();
        r.setCode(code);
        r.setMessage(message);
        r.setData(object);
        return r;
    }

    public static BaseResult genSuccessBaseResultMsg(String msg) {
        BaseResult r = new BaseResult();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(msg);
        return r;
    }

    public static BaseResult genSuccessResultPage(Object o,Long totalCount) {
        BaseResult r = new BaseResult();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMsg());
        r.setData(o);
        r.setTotalCount(totalCount);
        return r;
    }
}
