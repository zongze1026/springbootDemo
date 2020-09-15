package com.zongze.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Date 2020/9/4 17:24
 * @Created by xzz
 */
@Component
public class NettyServerStartHandler implements ApplicationRunner {
    @Autowired
    private NettyServer nettyServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        nettyServer.start();
    }
}
