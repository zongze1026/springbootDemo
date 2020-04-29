package com.zongze.controller;
import com.zongze.entity.ResultResp;
import com.zongze.entity.WXConstants;
import com.zongze.service.WechatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class WechatController {

    @Autowired
    private WechatService wxService;

    /**
     * 微信接入
     * signature微信加密签名,timestamp时间戳,nonce随机数,echostr随机字符串
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @GetMapping("wx/start")
    public void wxStart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        String[] signArr = {WXConstants.TOKEN, timestamp, nonce};
        Arrays.sort(signArr);
        StringBuilder builder = new StringBuilder();
        for (String para : signArr) {
            builder.append(para);
        }
        String signString = wxService.signature(builder.toString());

        //对比签名字符串
        if (StringUtils.equalsIgnoreCase(signature, signString)) {
            System.out.println("微信接入成功!");
            response.getWriter().print(echostr);
        } else {
            System.out.println("微信接入失败!");
        }
    }


    /**
     * 自定义菜单
     */
    @PostMapping("create/menu")
    public ResultResp createMenu() {
        if (wxService.createMenu()) {
            return ResultResp.succ();
        }
        return ResultResp.fail("创建菜单失败！");
    }



}
