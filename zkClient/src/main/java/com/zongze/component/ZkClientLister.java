package com.zongze.component;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Date 2020/12/15 10:59
 * @Created by xiezz
 */
@Component
public class ZkClientLister implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        String server = "192.168.235.201:2181";

        ZkClient zkClient = new ZkClient(server, 20000);

        subChildNode(zkClient);



    }

    //监听节点变化，不监听节点数据变化
    private void subChildNode(ZkClient zkClient) {
        zkClient.subscribeChildChanges("/test", (parentPath,childNodes)->{
            System.out.println(parentPath);
            for (String child:childNodes){
                System.out.println(ZkClientLister.class.getName()+"   子节点变化"+child);
            }
        });

    }

}
