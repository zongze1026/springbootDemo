package com.zongze.filter;

import com.zongze.component.CounterLimiter;
import com.zongze.util.IPAddressHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Create By xzz on 2019/12/20
 */
public class RequestLimitFilter implements Filter {

    private CounterLimiter counterLimiter;



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String clientIP = IPAddressHelper.getClientIP(request);
        if (StringUtils.isNotBlank(clientIP) && !StringUtils.equalsIgnoreCase(request.getMethod(), "OPTION")) {
            if (!counterLimiter.doLimit(clientIP.trim())) {
                response(servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    private void response(ServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("ip超过访问限制");
        writer.flush();
        writer.close();
    }


    public RequestLimitFilter(CounterLimiter counterLimiter) {
        this.counterLimiter = counterLimiter;
    }
}
