package com.zongze.security.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/8/27 10:55
 * @Created by xzz
 */
@Component
public class UserService implements UserDetailsService {


    @Value("${spring.security.user.name}")
    private String name;
    @Value("${spring.security.user.password}")
    private String password;
    @Autowired
    private BCryptPasswordEncoder encoder;

    private List<SimpleGrantedAuthority> auths = new ArrayList<SimpleGrantedAuthority>() {{
        add(new SimpleGrantedAuthority("ROLE_admin"));
        add(new SimpleGrantedAuthority("ROLE_root"));
    }};

    private List<SimpleGrantedAuthority> customer = new ArrayList<SimpleGrantedAuthority>() {{
        add(new SimpleGrantedAuthority("ROLE_admin"));
    }};


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(name + "=======" + s + "======" + password);
        if(!s.equals("xzz")){
            return new User(s, password, customer);
        }
        return new User(s, encoder.encode(password), auths);
//        return new User(s, password, auths);
    }


}
