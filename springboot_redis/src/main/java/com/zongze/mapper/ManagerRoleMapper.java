package com.zongze.mapper;

import com.zongze.model.ManagerRole;

public interface ManagerRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerRole record);

    int insertSelective(ManagerRole record);

    ManagerRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerRole record);

    int updateByPrimaryKey(ManagerRole record);
}