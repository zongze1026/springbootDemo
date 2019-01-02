package com.zongze.controller;

import com.zongze.config.shiro.UserRealm;
import com.zongze.entity.User;
import com.zongze.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Create By xzz on 2018/12/24
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRealm userRealm;


    @PostMapping("login")
    public Object login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
        subject.login(token);
        User principal = (User) subject.getPrincipal();
        return "success";
    }


    @RequestMapping("check")
    @RequiresRoles("tou")
    public Object check(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("==============perm====================");
        System.out.println(subject.isPermitted("sys:menu:view"));
        System.out.println(subject.isPermitted("sys:menu:delete"));
        System.out.println(subject.isPermitted("sys:menu:list"));
        System.out.println(subject.isPermitted("sys:menu:add"));
        System.out.println("===================role===========");
        System.out.println(subject.hasRole("admin"));
        System.out.println(subject.hasRole("tou"));
        System.out.println(subject.hasRole("cus"));
        return "权限验证通过";
    }


    @PostMapping("clear")
    public Object check2() {
        ShiroUtil.clearCache();
        return "缓存清除成功";
    }


}
