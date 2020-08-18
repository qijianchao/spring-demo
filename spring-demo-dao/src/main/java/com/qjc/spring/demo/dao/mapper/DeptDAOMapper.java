package com.qjc.spring.demo.dao.mapper;

import com.qjc.spring.demo.dao.entity.DeptDAO;
import com.qjc.spring.demo.dao.entity.DeptDAOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptDAOMapper {
    int countByExample(DeptDAOExample example);

    int deleteByExample(DeptDAOExample example);

    int deleteByPrimaryKey(Integer deptno);

    int insert(DeptDAO record);

    int insertSelective(DeptDAO record);

    List<DeptDAO> selectByExample(DeptDAOExample example);

    DeptDAO selectByPrimaryKey(Integer deptno);

    int updateByExampleSelective(@Param("record") DeptDAO record, @Param("example") DeptDAOExample example);

    int updateByExample(@Param("record") DeptDAO record, @Param("example") DeptDAOExample example);

    int updateByPrimaryKeySelective(DeptDAO record);

    int updateByPrimaryKey(DeptDAO record);
}