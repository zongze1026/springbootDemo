package com.zongze.component;
import com.zongze.utils.RedisUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Create By xzz on 2019/4/3
 */
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {


    /**
     * 服务启动后会执行run方法；redisUtil还没有被初始化，不可用
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============redis====================");
        RedisUtil.set("command:test","command",10l);
    }
}
