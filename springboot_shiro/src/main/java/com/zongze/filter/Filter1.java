package com.zongze.filter;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Create By xzz on 2019/2/22
 */
public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("================filter01:excutor=========================");
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        PrintWriter writer = response.getWriter();
        writer.write(Filter1.class.getName()+":执行中！！");
        writer.flush();
        writer.close();
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
