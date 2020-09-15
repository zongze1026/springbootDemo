package com.zongze.mapper;

import com.zongze.model.ManagerRoleUser;

public interface ManagerRoleUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerRoleUser record);

    int insertSelective(ManagerRoleUser record);

    ManagerRoleUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerRoleUser record);

    int updateByPrimaryKey(ManagerRoleUser record);
}