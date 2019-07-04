package com.zongze.dao;

import com.zongze.entity.button.ParentButton;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Create By xzz on 2019/7/4
 */
@Mapper
public interface ButtonMapper {

    List<ParentButton> createMenu();



}
