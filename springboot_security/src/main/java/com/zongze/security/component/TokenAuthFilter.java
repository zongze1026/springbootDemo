package com.zongze.security.component;

import com.alibaba.fastjson.JSON;
import com.zongze.security.component.entity.AuthUser;
import com.zongze.security.component.entity.BaseResult;
import com.zongze.security.component.entity.ResultCode;
import com.zongze.security.component.entity.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @Date 2020/8/27 10:31
 * @Created by xzz
 */
@WebFilter("/*")
@Component
public class TokenAuthFilter extends OncePerRequestFilter {
    private static final String TOKEN = "token";
    @Autowired
    private UserTokenOperationHandler userTokenOperationHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (!ignore(httpServletRequest)) {
            String token = httpServletRequest.getHeader(TOKEN);
            AuthUser authUser;
            if (StringUtils.isEmpty(token)||null == (authUser = userTokenOperationHandler.getUserDetail(token))) {
                response(httpServletResponse);
                return;
            }
            userTokenOperationHandler.flushToken(token, 30, null);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    private boolean ignore(HttpServletRequest request) {
        String[] ignorePath = {"/headOffice-manager/v2/api-docs", "/headOffice-manager/configuration/ui", "/headOffice-manager/swagger-resources",
                "/headOffice-manager/login", "/headOffice-manager/configuration/security", "/headOffice-manager/swagger-ui.html",
                "/headOffice-manager/webjars/**"};
        String uri = request.getRequestURI();
        return Arrays.stream(ignorePath).anyMatch(ele -> ele.equals(uri));
    }


    private void response(HttpServletResponse response){
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            BaseResult baseResult = ResultGenerator.genFailBaseResultByRsultCode(ResultCode.LOGINAUTHENTERROR);
            writer.write(JSON.toJSONString(baseResult));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
