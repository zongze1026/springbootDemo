package com.zongze.controller;

import com.alibaba.fastjson.JSON;
import com.zongze.entity.ResultResp;
import com.zongze.entity.WXConstants;
import com.zongze.entity.button.Button;
import com.zongze.entity.button.ParentButton;
import com.zongze.entity.button.SubButton;
import com.zongze.service.WXService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create By xzz on 2019/7/2
 * 微信开发接入
 */
@RestController
public class WXController {

    @Autowired
    private WXService wxService;

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
        WXService wxService = new WXService();
        List<ParentButton> parentButtons = new ArrayList<>();
        for (int n = 0; n < 3; n++) {
            ParentButton parentButton = new ParentButton();
            parentButton.setName("订单购买");
            List<SubButton> subButtons = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                SubButton subButton = new SubButton();
                subButton.setType("view");
                subButton.setUrl("https://www.baidu.com");
                subButton.setName("按钮" + i + 1);
                subButtons.add(subButton);
            }
            parentButton.setSub_button(subButtons);
            parentButtons.add(parentButton);
        }
        Button button = new Button();
        button.setButton(parentButtons);
        System.out.println(JSON.toJSONString(button));
        if (wxService.createMenu(JSON.toJSONString(button))) {
            return ResultResp.succ();
        }
        return ResultResp.fail("创建菜单失败！");
    }


}
