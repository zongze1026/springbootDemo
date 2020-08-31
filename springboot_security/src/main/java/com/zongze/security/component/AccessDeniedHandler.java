package com.zongze.security.component;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Date 2020/8/27 11:15
 * @Created by xzz
 */
@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler, AuthenticationEntryPoint {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        responseClient(httpServletResponse,"登入用户无权访问");
    }


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        responseClient(httpServletResponse,"游客用户无权访问");
    }


    private void responseClient(HttpServletResponse httpServletResponse,String msg) throws IOException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(msg);
        writer.flush();
        writer.close();
    }


}
