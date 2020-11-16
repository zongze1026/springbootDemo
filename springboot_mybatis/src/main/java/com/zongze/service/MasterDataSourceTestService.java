package com.zongze.service;

import com.zongze.annotation.DynamicDatasource;
import com.zongze.domain.User;
import com.zongze.mapper.MallProtocolUploadFileMapper;
import com.zongze.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create By xzz on 2020/6/18
 */
@Service
public class MasterDataSourceTestService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Slave01DataSourceTestService slave01Service;
    @Autowired
    private Slave02DataSourceTestService slave02Service;


    @DynamicDatasource("master")
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        int i = slave01Service.addUser(user);
        int n = slave02Service.addUser(user);
        if (i > 0 && n > 0) {
            int num = userMapper.add(user);
            System.out.println(num);
        }
        return i;
    }


}
