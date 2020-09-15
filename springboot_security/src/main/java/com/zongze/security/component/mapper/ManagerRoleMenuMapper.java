package com.zongze.security.component.mapper;


import com.zongze.security.component.entity.AddManagerRoleBo;
import com.zongze.security.component.entity.ManagerMenu;
import com.zongze.security.component.entity.ManagerRoleMenu;

import java.util.List;

public interface ManagerRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerRoleMenu record);

    int insertSelective(ManagerRoleMenu record);

    ManagerRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerRoleMenu record);

    int updateByPrimaryKey(ManagerRoleMenu record);

    List<ManagerMenu> queryAllMenuByRoleId(List<Long> roleIds);

    List<ManagerMenu> queryAllMenuForRoot();

    void addMenuByRoleId(AddManagerRoleBo managerRoleBo);

    int deleteOldMenuByRoleId(Long roleId);
}