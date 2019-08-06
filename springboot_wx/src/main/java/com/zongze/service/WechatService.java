package com.zongze.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zongze.dao.ButtonMapper;
import com.zongze.entity.AccessToken;
import com.zongze.entity.WXConstants;
import com.zongze.entity.button.Button;
import com.zongze.entity.button.ParentButton;
import com.zongze.util.HttpUtil;
import com.zongze.util.MD5Util;
import com.zongze.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Create By xzz on 2019/7/2
 */
@Service
public class WechatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatService.class);

    private AccessToken token = null;


    @Autowired
    private ButtonMapper buttonMapper;




    /**
     * 参数参与签名
     */
    public String signature(String sign) {
        return MD5Util.encrypt(sign);
    }


    /**
     * 获取accessToken
     */
    public AccessToken getToken() {
        if (null == token || isValid(token)) {
            token = accessToken();
        }
        return token;
    }

    private AccessToken accessToken() {
        String tokenJson = HttpUtil.get(WXConstants.ACCESS_TOKEN);
        AccessToken accessToken = jsonToJavaObject(tokenJson, AccessToken.class);
        accessToken.setExpiresIn((accessToken.getExpiresIn() * 1000 + System.currentTimeMillis()));
        return accessToken;
    }

    private static <T> T jsonToJavaObject(String jsonString, Class<T> clazz) {
        StringBuffer buffer = new StringBuffer();
        List<Field> fields = ObjectUtil.getFields(clazz);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
        T t = null;
        try {
            t = clazz.newInstance();
            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                if (key.contains("_")) {
                    String[] strings = key.split("_");
                    String tempKey;
                    for (int i = 0; i < strings.length; i++) {
                        if (i == 0) {
                            buffer.append(strings[i]);
                            continue;
                        }
                        tempKey = strings[i];
                        buffer.append(tempKey.substring(0, 1).toUpperCase()).append(tempKey.substring(1));
                    }
                    tempKey = buffer.toString();
                    buffer.setLength(0);
                    for (Field field : fields) {
                        if (StringUtils.equalsIgnoreCase(field.getName(), tempKey)) {
                            field.setAccessible(true);
                            if (field.getType() == Integer.class) {
                                field.set(t, Integer.valueOf(entry.getValue() + ""));
                            } else if (field.getType() == Long.class) {
                                field.set(t, Long.valueOf(entry.getValue() + ""));
                            } else {
                                field.set(t, entry.getValue());
                            }
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    private boolean isValid(AccessToken token) {
        return System.currentTimeMillis() > token.getExpiresIn();
    }


    public boolean createMenu() {
        List<ParentButton> menu = buttonMapper.createMenu();
        Button button = new Button();
        button.setButton(menu);
        System.out.println(JSON.toJSONString(menu));
        AccessToken token = getToken();
        String result = HttpUtil.postForJson(WXConstants.createMenuUrl(token.getAccessToken()), JSON.toJSONString(button));
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (StringUtils.equalsIgnoreCase("0", jsonObject.getString("errcode"))) {
            return true;
        } else {
            LOGGER.info("创建菜单失败:{}", jsonObject.getString("errmsg"));
        }
        return false;
    }




}
