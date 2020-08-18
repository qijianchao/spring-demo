package com.qjc.spring.demo.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qjc.spring.demo.biz.service.DeptService;
import com.qjc.spring.demo.dao.entity.DeptDAO;
import com.qjc.spring.demo.dao.entity.DeptDAOExample;
import com.qjc.spring.demo.dao.mapper.DeptDAOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDAOMapper deptDAOMapper;

    @Override
    @Transactional
    public List<DeptDAO> queryDept(String deptName, Integer deptNo) {
        DeptDAOExample deptDAOExample = new DeptDAOExample();
        deptDAOExample.createCriteria().andDeptnameLike("%" + deptName + "%");
        deptDAOExample.or(deptDAOExample.createCriteria().andDeptnoEqualTo(deptNo));
        deptDAOExample.setOrderByClause("deptNo desc");
        PageHelper.startPage(2,2);
        List<DeptDAO> deptDAOList = deptDAOMapper.selectByExample(deptDAOExample);
        PageInfo<DeptDAO> p = new PageInfo<DeptDAO>(deptDAOList); // 实例化PageInfo
        List<DeptDAO> deptDAOList2 = p.getList(); // 总记录数
        System.out.println(p.getTotal());
        DeptDAO deptDAO = deptDAOMapper.selectByPrimaryKey(1);
        return deptDAOList;
    }
}
