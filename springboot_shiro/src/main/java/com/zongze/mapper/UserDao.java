package com.zongze.mapper;

import com.zongze.entity.Menu;
import com.zongze.entity.Role;
import com.zongze.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

/**
 * Create By xzz on 2018/12/24
 */
@Mapper
public interface UserDao {

    User userInfo(String userName);

    Set<Role> geRole(String userName);

    Set<Menu> getPerm(String userName);

    @Update("update user set userName = #{userName} where id = #{id}")
    int eidt(User user);

    List<User> userList(User user);

    @Update("update user set age=age-1,version=version+1 where id = #{id} and version=#{version}")
    int sellSync(User user);

    @Update("update user set age=age-1 where id = #{id}")
    int sell(User user);


}
