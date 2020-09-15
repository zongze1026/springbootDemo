package com.zongze.security.component.mapper;


import com.zongze.security.component.entity.ManagerRole;
import com.zongze.security.component.entity.ManagerRoleListVo;

import java.util.List;

public interface ManagerRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerRole record);

    int insertSelective(ManagerRole record);

    ManagerRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerRole record);

    int updateByPrimaryKey(ManagerRole record);

    List<ManagerRole> queryRolesByUserId(Integer userId);

    int queryRoleInfoByRoleName(ManagerRole managerRole);

    List<ManagerRoleListVo> queryManagerRoleListInfo();

    int countUserNumByRoleId(Long roleId);

    List<ManagerRole> queryAllRoles();
}
