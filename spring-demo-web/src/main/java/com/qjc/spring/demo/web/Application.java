package com.qjc.spring.demo.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.qjc.spring.demo")
@MapperScan("com.qjc.spring.demo.dao.mapper")
@ServletComponentScan("com.qjc.spring.demo.web.filter")
public class Application {

    public static void main(String[] args) {
        // 初始化日志路径 user.dir 表示该项目根目录
        String rootPath = System.getProperty("user.dir");
        System.setProperty("log.base",rootPath);

        SpringApplication.run(Application.class, args);
    }

}
