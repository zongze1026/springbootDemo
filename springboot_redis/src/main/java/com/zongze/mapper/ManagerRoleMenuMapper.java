package com.zongze.mapper;

import com.zongze.model.ManagerRoleMenu;

public interface ManagerRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerRoleMenu record);

    int insertSelective(ManagerRoleMenu record);

    ManagerRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerRoleMenu record);

    int updateByPrimaryKey(ManagerRoleMenu record);
}