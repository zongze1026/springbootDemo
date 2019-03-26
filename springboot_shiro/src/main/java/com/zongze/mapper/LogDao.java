package com.zongze.mapper;

import com.zongze.entity.Logger;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


/**
 * Create By xzz on 2018/12/24
 */
@Mapper
public interface LogDao {

    @Insert("INSERT into log(title,uri,content,req_param,resp_result,ip,operator)values(#{title},#{uri},#{content},#{reqParam},#{respResult},#{ip},#{operator})")
    int add(Logger log);

}
