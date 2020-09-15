package com.zongze.security.component;

import com.zongze.security.component.entity.*;
import com.zongze.security.component.mapper.ManagerRoleMenuMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date 2020/8/27 10:19
 * @Created by xzz
 */
@Component
public class LoginSuccessHandler extends ResponseClientHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ManagerRoleMenuMapper managerRoleMenuMapper;
    @Autowired
    private UserTokenOperationHandler userTokenFlushHandler;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        List<ManagerRole> roles = authUser.getRoles();
        List<Long> roleIds = null;
        List<ManagerMenu> menus;
        boolean rootRole = roles.stream().anyMatch(managerRole -> RoleTypeEnum.ROLE_ROOT.getRoleLevel().equals(managerRole.getRoleLevel()));
        if(!rootRole){
            roleIds = roles.stream().map(managerRole -> managerRole.getId()).collect(Collectors.toList());
            menus = managerRoleMenuMapper.queryAllMenuByRoleId(roleIds);
        }else{
            menus = managerRoleMenuMapper.queryAllMenuForRoot();
        }
        UserLoginResVo vo = new UserLoginResVo();
        BeanUtils.copyProperties(authUser,vo );
        vo.setMenus(menus);
        vo.setToken(userTokenFlushHandler.flushToken(null, 30, authUser));
        responseSuccess(httpServletResponse, ResultCode.SUCCESS, vo);
    }





}
