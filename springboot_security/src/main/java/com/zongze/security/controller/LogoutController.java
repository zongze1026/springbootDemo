package com.zongze.security.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @Date 2020/8/27 14:04
 * @Created by xzz
 */
@RestController
public class LogoutController {


    @GetMapping("/login/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.stream().forEach(auth->{
            String authority = ((GrantedAuthority) auth).getAuthority();
            System.out.println(authority);
        });
        Object credentials = authentication.getCredentials();
        System.out.println(JSON.toJSONString(credentials));
        Object principal = authentication.getPrincipal();
        System.out.println(JSON.toJSONString(principal));
        new SecurityContextLogoutHandler().logout(request, response,authentication );
    }





}
