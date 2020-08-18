package com.qjc.spring.demo.dao.tool;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GeneratorMybatis {
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        // String configPath = GeneratorMybatis.class.getResource("").getPath();
        ClassPathResource classPathResource = new ClassPathResource("mybatis/mybatis-generator.xml");
        InputStream inputStream = classPathResource.getInputStream();

        //Configuration configuration = cp.parseConfiguration(new File("spring-demo/spring-demo-dao/src/main/resources/mybatis/mybatis-generator.xml"));
        Configuration configuration = cp.parseConfiguration(inputStream);
        DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, defaultShellCallback, warnings);
        myBatisGenerator.generate(null);
        warnings.forEach(System.out::println);
    }
}
