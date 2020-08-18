package com.qjc.spring.demo.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/hello/*", filterName = "myTestFilter")
@Order(1)
public class MyTestFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(MyTestFilter.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //servletResponse.setCharacterEncoding("UTF-8");
        //servletResponse.setContentType("application/json; charset=utf-8");
        String token = req.getHeader("token");
        boolean isFilter = false;
        if (null == token || token.isEmpty()) {
            servletResponse.getOutputStream().print("no token,no way");
        } else {
            logger.info("token filter过滤ok!");
            if(!stringRedisTemplate.hasKey(token)) {
                stringRedisTemplate.opsForValue().set(token, "1");
            } else {
                stringRedisTemplate.boundValueOps(token).increment(1);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }

        System.out.println("Execute cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
