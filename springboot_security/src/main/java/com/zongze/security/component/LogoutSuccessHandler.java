package com.zongze.security.component;

import com.dayu.model.base.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Date 2020/8/27 10:24
 * @Created by xzz
 */
@Component
public class LogoutSuccessHandler extends ResponseClientHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
       responseSuccess(httpServletResponse, ResultCode.SUCCESS,null);
    }
}
