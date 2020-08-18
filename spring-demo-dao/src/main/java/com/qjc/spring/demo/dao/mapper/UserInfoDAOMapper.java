package com.qjc.spring.demo.dao.mapper;

import com.qjc.spring.demo.dao.entity.UserInfoDAO;

public interface UserInfoDAOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfoDAO record);

    int insertSelective(UserInfoDAO record);

    UserInfoDAO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfoDAO record);

    int updateByPrimaryKey(UserInfoDAO record);
}