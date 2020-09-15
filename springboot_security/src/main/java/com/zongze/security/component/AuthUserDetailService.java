package com.zongze.security.component;

import com.zongze.security.component.entity.AuthUser;
import com.zongze.security.component.entity.ManagerRole;
import com.zongze.security.component.entity.ManagerUser;
import com.zongze.security.component.entity.ResultCode;
import com.zongze.security.component.mapper.ManagerRoleMapper;
import com.zongze.security.component.mapper.ManagerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2020/9/10 11:39
 * @Created by xzz
 */
@Service
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    private ManagerUserMapper managerUserMapper;
    @Autowired
    private ManagerRoleMapper managerRoleMapper;


    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        ManagerUser managerUser = managerUserMapper.selectEffManageUserByPhone(phone);
        if(null == managerUser){
            throw new UsernameNotFoundException(ResultCode.LOGINERROR.getMsg());
        }
        List<ManagerRole> roles = managerRoleMapper.queryRolesByUserId(managerUser.getId());
        return new AuthUser(managerUser,roles);
    }
}
