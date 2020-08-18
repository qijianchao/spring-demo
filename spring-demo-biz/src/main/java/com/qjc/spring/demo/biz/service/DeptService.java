package com.qjc.spring.demo.biz.service;

import com.qjc.spring.demo.dao.entity.DeptDAO;

import java.util.List;

public interface DeptService {
    List<DeptDAO> queryDept(String deptName, Integer deptNo);
}
