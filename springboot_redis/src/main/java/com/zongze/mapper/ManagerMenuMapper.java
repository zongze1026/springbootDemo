package com.zongze.mapper;

import com.zongze.model.ManagerMenu;

public interface ManagerMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerMenu record);

    int insertSelective(ManagerMenu record);

    ManagerMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerMenu record);

    int updateByPrimaryKey(ManagerMenu record);
}