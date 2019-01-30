package com.zongze.config.shiro;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;


/**
 * Create By xzz on 2019/1/28
 */
public class MysessionManager extends DefaultWebSessionManager {

    /**
     * 获取请求头中key为“Authorization”的value == sessionId
     */
    private static final String TOKEN ="token";


    private static final String REFERENCED_SESSION_ID_SOURCE = "header";


    /**
     *  @Description shiro框架 自定义session获取方式<br/>
     *  可自定义session获取规则。这里采用ajax请求头 {@link }携带sessionId的方式
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String sessionId = WebUtils.toHttp(request).getHeader(TOKEN);
        if (StringUtils.isNotEmpty(sessionId)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }
        return super.getSessionId(request, response);
    }







}
