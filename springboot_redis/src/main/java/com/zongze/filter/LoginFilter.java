package com.zongze.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * Create By xzz on 2019/8/6
 */
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (!ignore(request)) {

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //放行swagger和不过滤的路径
    private boolean ignore(HttpServletRequest request) {
        String[] ignorePath = {"/sms/send", "/user/register"};
        String uri = request.getRequestURI();
        if (uri.contains("swagger") || uri.contains("webjars") || uri.contains("v2")) {
            return true;
        }
        return Arrays.stream(ignorePath).anyMatch(ele -> ele.equals(uri));
    }


    @Override
    public void destroy() {

    }

}
