package com.zongze.security.component;

import com.zongze.security.component.entity.ResultCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
public class LoginFailureHandler extends ResponseClientHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultCode resultCode ;
        if (e instanceof BadCredentialsException) {
            resultCode = ResultCode.LOGINERROR;
        } else if (e instanceof LockedException) {
            resultCode = ResultCode.USERLOCK;
        } else {
            resultCode = ResultCode.INTERNAL_SERVER_ERROR;
        }
        responseFail(httpServletResponse, resultCode);
    }
}
