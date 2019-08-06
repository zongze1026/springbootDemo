package com.zongze.entity;

/**
 * Create By xzz on 2019/7/3
 */
public class WXConstants {
    //微信公众号上的配置
    public static final String TOKEN = "wxtoken";
    public static final String APP_ID = "wx38f204cdd185e445";
    public static final String ENCODING_AES_KEY = "z7SEH6aGVkQVgEvQ2eZIi9uVcXqlKrE2TaMj9hd3AHH";
    public static final String APP_SECRET = "3efa0ea0d29f3cfa2b46a157cf52759c   ";
    public static final String CONFIG_URL = "http://yitkeji.free.idcfengye.com/wx/start";

    //自定义菜单接口
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    public static String createMenuUrl(String token) {
        return String.format(CREATE_MENU_URL, token);
    }

    //获取accessToken地址
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;

    //获取用户信息
    public static final String getUserInfo(String token, String openID) {
        return String.format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN", token, openID);
    }



}
