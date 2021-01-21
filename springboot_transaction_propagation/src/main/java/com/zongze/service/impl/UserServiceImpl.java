package com.zongze.service.impl;

import com.zongze.mapper.CourseInfoMapper;
import com.zongze.mapper.UserInfoMapper;
import com.zongze.mapper.UserMapper;
import com.zongze.model.CourseInfo;
import com.zongze.model.User;
import com.zongze.model.UserInfo;
import com.zongze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Create By xzz on 2018/11/30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CourseInfoMapper courseInfoMapper;

    //required
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(User user) {
        User u;
        for (int i = 0; i < 1000000; i++) {
            u = new User();
            u.setUserName("张三" + i);
            u.setAge(i);
            userMapper.add(u);
            System.out.println(i);

        }
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

    @Override
    public void addUserAndCourse() {
        String[] name = {"张三", "李四", "王五", "赵六", "田七", "赵八", "老九"};
        String[] courseName = {"语文", "数学", "化学", "英语", "物理", "体育", "生物"};
        Integer[] type = {1, 2, 3, 4, 5, 6, 7};
        Integer[] times = {20, 30, 40, 50, 60, 70,80};

        for (int i = 1; i < 500001; i++) {
            int n = i % 6;
            int m = 0;
            UserInfo userInfo = new UserInfo();
            userInfo.setAge(i % 99);
            userInfo.setUserName(name[n]);
            userInfoMapper.insertSelective(userInfo);
            CourseInfo course1 = new CourseInfo();
            CourseInfo course2 = new CourseInfo();
            course1.setCourseName(courseName[n]);
            if (n == 6) {
                m = n - 1;
            } else if (n == 0) {
                m = n + 1;
            } else {
                m = n + 1;
            }
            course1.setCourseName(courseName[n]);
            course1.setCourseType(type[n]);
            course2.setCourseName(courseName[m]);
            course2.setCourseType(type[m]);
            course1.setUserId(userInfo.getId());
            course2.setUserId(userInfo.getId());
            course1.setCourseTime(times[n]);
            course2.setCourseTime(times[m]);
            courseInfoMapper.insertSelective(course1);
            courseInfoMapper.insertSelective(course2);

        }


    }

}
