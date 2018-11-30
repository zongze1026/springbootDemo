package com.zongze.service;

import com.zongze.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create By xzz on 2018/11/30
 */
public interface UserService {

    void add(User user);

    void add_error(User user);

     void add2(User user);

     void add_error2(User user);

    void add3(User user);

    void add_error3(User user);


}
