package com.zongze.security.component;

import com.alibaba.fastjson.JSON;
import com.zongze.security.component.entity.BaseResult;
import com.zongze.security.component.entity.ResultCode;
import com.zongze.security.component.entity.ResultGenerator;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Date 2020/9/10 15:51
 * @Created by xzz
 */
public class ResponseClientHandler {

    protected void responseFail(HttpServletResponse response, ResultCode resultCode) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            BaseResult baseResult = ResultGenerator.genFailBaseResultByRsultCode(resultCode);
            writer.write(JSON.toJSONString(baseResult));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void responseSuccess(HttpServletResponse response, ResultCode resultCode, Object data) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            BaseResult baseResult = ResultGenerator.genResult(resultCode.getCode(), resultCode.getMsg(), data);
            writer.write(JSON.toJSONString(baseResult));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
