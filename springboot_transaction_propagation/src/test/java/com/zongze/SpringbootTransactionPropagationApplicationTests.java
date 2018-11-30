package com.zongze;

import com.zongze.service.UserManageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTransactionPropagationApplicationTests {

    @Autowired
    private UserManageService service;

    /**
     * PROPAGATION_REQUIRED   如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中
     * PROPAGATION_REQUIRES_NEW    新建事务，如果当前存在事务，把当前事务挂起。
     * PROPAGATION_SUPPORTS   支持当前事务，如果当前没有事务，就以非事务方式执行。
     * PROPAGATION_NOT_SUPPORTED   以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
     * PROPAGATION_NESTED   	如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。
     * PROPAGATION_MANDATORY  使用当前的事务，如果当前没有事务，就抛出异常。
     * PROPAGATION_NEVER    以非事务方式执行，如果当前存在事务，则抛出异常。
     */


    @Test
    public void no_requied() {
        service.no_requied();
    }


    @Test
    public void have_required() {
        service.have_required();
    }


    @Test
    public void have_required_new() {
        service.have_required_new();
    }


    @Test
    public void no_required_new() {
        service.no_required_new();
    }


    @Test
    public void have_nested() {
        service.have_nested();
    }

    @Test
    public void have_nested_nocatch() {
        service.have_nested_nocatch();
    }


    @Test
    public void have_nested_error() {
        service.have_nested_error();
    }




}
