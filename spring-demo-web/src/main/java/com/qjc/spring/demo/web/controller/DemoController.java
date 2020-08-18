package com.qjc.spring.demo.web.controller;

import com.qjc.spring.demo.biz.service.DeptService;
import com.qjc.spring.demo.biz.service.LoginService;
import com.qjc.spring.demo.common.utils.SnowFlake;
import com.qjc.spring.demo.dao.entity.DeptDAO;
import com.qjc.spring.demo.web.aspect.WebLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Api(value = "测试控制器", tags = "测试控制器")
@RestController
@RequestMapping("/hello")
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DeptService deptService;

    @Autowired
    private LoginService loginService;

    @WebLog(description = "自定义的web日志注解")
    @ApiOperation(value = "你好世界")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestHeader(value = "token") String token, @RequestParam String deptName, @RequestParam Integer deptNo) {
        // 日志打印测试
        logger.trace("This is a trace msg");
        logger.debug("This is a debug msg");
        logger.info("This is a info msg");
        logger.warn("This is a warn msg");
        logger.error("This is a error msg");

        // mybatis操作测试
        List<DeptDAO> deptDAOList = deptService.queryDept(deptName, deptNo);

        int count = 1;
        for (DeptDAO deptDAO : deptDAOList) {
            logger.info("第{}个查询结果：{},{},{}", count++, deptDAO.getDeptno(), deptDAO.getDeptname(), deptDAO.getDeptdesc());
        }

        // 雪花算法测试
        SnowFlake snowFlake = new SnowFlake(2, 3);

        long start = System.currentTimeMillis();
        // 用雪花算法生成全局流水号，并打印生成时间
        for (int i = 0; i < 1; i++) {
            logger.info("第{}个流水号是{}", i, snowFlake.nextId());
        }
        // 雪花算法时间打印
        System.out.println(System.currentTimeMillis() - start);

        logger.info("token：{},检查结果为{}", token, loginService.checkToken(token));
        logger.info("token：{},访问量为{}", token, loginService.getTokenVisits(token));
        // loginService.addTokenVisits(token); // 增加访问量,由MyTestFilter完成该工作

        return "Hello World";
    }
}
