package com.zongze.service.impl;

import com.zongze.mapper.UserMapper;
import com.zongze.model.User;
import com.zongze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.*;

/**
 * Create By xzz on 2018/11/30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;

    //required
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(User user) {
        userMapper.add(user);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCompletion(int status) {
                if(status == TransactionSynchronization.STATUS_COMMITTED){
                    System.out.println("事务执行成功");
                }else{
                    System.out.println("事务执行失败");
                }
            }
        });
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add_error(User user) {
        userMapper.add(user);
        throw new RuntimeException();
    }

    //required_new
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void add2(User user) {
        userMapper.add(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void add_error2(User user) {
        userMapper.add(user);
        throw new RuntimeException();
    }


    //nested
    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void add3(User user) {
        userMapper.add(user);
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void add_error3(User user) {
        userMapper.add(user);
        throw new RuntimeException();
    }


    @Override
    public void transactionTemplate(User user) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                userMapper.add(user);
                return null;
            }
        });

    }


}
