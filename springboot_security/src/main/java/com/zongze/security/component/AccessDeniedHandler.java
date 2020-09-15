package com.zongze.security.component;

import com.zongze.security.component.entity.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Date 2020/8/27 11:15
 * @Created by xzz
 */
@Component
public class AccessDeniedHandler extends ResponseClientHandler implements org.springframework.security.web.access.AccessDeniedHandler, AuthenticationEntryPoint {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
       responseFail(httpServletResponse, ResultCode.AUTHENTERROR);
    }


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        responseFail(httpServletResponse, ResultCode.AUTHENTERROR);
    }



}
