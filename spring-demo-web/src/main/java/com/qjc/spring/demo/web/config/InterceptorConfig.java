package com.qjc.spring.demo.web.config;

import com.qjc.spring.demo.web.interceptor.MyTestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyTestInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger**");// 后面的exclude没生效
    }
}
