package com.zongze.controller;

import com.thoughtworks.xstream.XStream;
import com.zongze.service.SignCheckService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Create By xzz on 2019/7/2
 * 微信开发接入
 */
@RestController
public class WXController {

    @Autowired
    private SignCheckService signCheckService;

    //signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
    //timestamp	时间戳
    //nonce	随机数
    //echostr	随机字符串
    @GetMapping("check/sign")
    public void checkSign(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        System.out.println(signature + "==" + timestamp + "==" + nonce + "==" + echostr);

        //1）将token、timestamp、nonce三个参数进行字典序排序
        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        String[] signArr = {"wxtoken", timestamp, nonce};
        Arrays.sort(signArr);
        StringBuilder builder = new StringBuilder();
        for (String para :signArr){
            builder.append(para);
        }
        String signString = signCheckService.checkSign(builder.toString());


        //对比签名字符串
        if(StringUtils.equalsIgnoreCase(signature,signString)){
            System.out.println("微信接入成功!");
            response.getWriter().print(echostr);
        }else{
            System.out.println("微信接入失败!");
        }

    }


}
