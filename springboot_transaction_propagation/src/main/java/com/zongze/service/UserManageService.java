package com.zongze.service;

import com.zongze.model.User;
import com.zongze.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create By xzz on 2018/11/30
 */
@Service
@SuppressWarnings("all")
public class UserManageService {

    @Autowired
    private UserServiceImpl userService;

    /**
     * PROPAGATION_REQUIRED   如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中
     * PROPAGATION_REQUIRES_NEW    新建事务，如果当前存在事务，把当前事务挂起。
     * PROPAGATION_SUPPORTS   支持当前事务，如果当前没有事务，就以非事务方式执行。
     * PROPAGATION_NOT_SUPPORTED   以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
     * PROPAGATION_NESTED   	如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。
     * PROPAGATION_MANDATORY  使用当前的事务，如果当前没有事务，就抛出异常。
     * PROPAGATION_NEVER    以非事务方式执行，如果当前存在事务，则抛出异常。
     */


    //测试required
    @Transactional(propagation = Propagation.REQUIRED)
    public void have_required() {
        /**
         *当前方法存在事务，两个方法都会加入事务，可以把它们看成时一个事务
         *
         * user2插入时抛出异常，整个事务回滚
         * user1失败
         * user2失败
         */
        requied();
    }

    public void no_requied() {
        /**
         *当前方法不存在事务，两个方法都会开启自己的事务
         *
         * user1插入成功
         * user2抛出异常，插入失败
         * 两个子事务互补影响
         *
         */
        requied();
    }

    private void requied() {
        User user1 = new User();
        user1.setUserName("zhangsan");
        user1.setAge(18);

        User user2 = new User();
        user2.setUserName("lisi");
        user2.setAge(28);
        userService.add(user1);
        userService.add_error(user2);
    }

    /***************************************************************************************/
    //测试requires_new

    //当前带事务
    @Transactional(propagation = Propagation.REQUIRED)
    public void have_required_new() {
        /**
         *当前虽然带有事务，但是事务会被挂起，两个方法都会开启子事务执行
         *
         * user1插入成功
         * user2抛出异常，插入失败
         * 两个子事务互补影响
         */
        required_new();
    }

    public void no_required_new() {
        /**
         *当前没有带事务，两个方法都会开启子事务执行
         *
         * user1插入成功
         * user2抛出异常，插入失败
         * 两个子事务互补影响，和当前带事务的运行结果一样
         */
        required_new();
    }


    private void required_new() {
        User user1 = new User();
        user1.setUserName("zhangsan");
        user1.setAge(18);

        User user2 = new User();
        user2.setUserName("lisi");
        user2.setAge(28);
        userService.add2(user1);
        userService.add_error2(user2);
    }


    /***************************************************************************************/
    //测试nested(嵌套事务)

    //当前带事务
    @Transactional(propagation = Propagation.REQUIRED)
    public void have_nested() {
        /**
         *当前存在事务，两个插入会开启两个事务作为子事务，如果当前程序没有抛出异常的话事务提交，否则回滚子事务全部回滚
         *
         * user1插入成功
         * user2子事务抛出异常回滚，插入失败
         * 两个子事务互补影响
         */
        //处理了异常，不影响当前事务提交
        try {
            nested();
        } catch (Exception e) {
            System.out.println("抛出异常了，当时不影响当前事务");
        }
    }


    //当前带事务
    @Transactional(propagation = Propagation.REQUIRED)
    public void have_nested_nocatch() {
        /**
         *当前存在事务，两个插入会开启两个事务作为子事务，如果当前程序没有抛出异常的话事务提交，否则回滚子事务全部回滚
         *
         * user1插入失败
         * user2子事务抛出异常回滚，插入失败
         *
         */
        //没有处理user2中的异常，当前事务回滚，整体回滚
        nested();
    }


    //当前带事务,当前抛出异常
    @Transactional(propagation = Propagation.REQUIRED)
    public void have_nested_error() {
        /**
         *当前虽然带有事务，但是事务会被挂起，两个方法都会开启子事务执行
         *
         * user1插入成功
         * user2抛出异常，插入失败
         * 两个子事务互补影响
         */
        nested();
        throw new RuntimeException();
    }


    public void no_nested() {
        /**
         *当前没有带事务，两个方法都会开启子事务执行
         *
         * user1插入成功
         * user2抛出异常，插入失败
         * 两个子事务互补影响，和当前带事务的运行结果一样
         */
        nested();
    }


    private void nested() {
        User user1 = new User();
        user1.setUserName("zhangsan");
        user1.setAge(18);

        User user2 = new User();
        user2.setUserName("lisi");
        user2.setAge(28);
        userService.add3(user1);
        userService.add_error3(user2);
    }


}
