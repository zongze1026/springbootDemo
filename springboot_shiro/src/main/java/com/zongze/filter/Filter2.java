package com.zongze.filter;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

/**
 * Create By xzz on 2019/2/22
 */
public class Filter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("================filter02:excutor=========================");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
