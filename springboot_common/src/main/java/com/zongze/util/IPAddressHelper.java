package com.zongze.util;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;

public class IPAddressHelper {

    public static String getClientIP(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = null;
        String[] headers = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        for (int i = 0; i < headers.length; i++) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader(headers[i]);
            }
        }
        if(StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.contains(",") ? ip.split(",")[0]: ip;
    }
}
