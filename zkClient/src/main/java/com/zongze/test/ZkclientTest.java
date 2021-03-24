package com.zongze.test;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * @Date 2020/12/15 10:47
 * @Created by xiezz
 */
public class ZkclientTest {


    public static void main(String[] args) throws InterruptedException {

        String server = "192.168.235.201:2181";

        ZkClient zkClient = new ZkClient(server, 100000);
        for (int i=0;i<10;i++){
            create(zkClient,"data"+i);
        }
//        create(zkClient);
//        delete(zkClient);
//        getData(zkClient);

//        update(zkClient);

//        subChildNode(zkClient);

//        subChildNodeData(zkClient);

//        for (int i=0;i<10;i++){
//            zkClient.create("/test/mm", "mm", CreateMode.PERSISTENT_SEQUENTIAL);
//        }






    }

    private static void subChildNodeData(ZkClient zkClient) throws InterruptedException {
        zkClient.subscribeDataChanges("/test", new IZkDataListener(){
            //节点变化处理器
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点变化path:"+s+"  "+"节点数据："+o);
            }

            //节点删除处理器
            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("节点删除path:"+s);
            }
        });


        zkClient.writeData("/test", "abcd");
        zkClient.deleteRecursive("/test");


        Thread.sleep(1000000000);
    }

    //监听节点变化，不监听节点数据变化
    private static void subChildNode(ZkClient zkClient) {
        zkClient.subscribeChildChanges("/test", (parentPath,childNodes)->{
            System.out.println(parentPath);
            for (String child:childNodes){
                System.out.println(child);
            }
        });

//        create(zkClient);

    }

    //更新节点数据
    private static void update(ZkClient zkClient) {
        zkClient.writeData("/test/aa","更新了数据2" );
        getData(zkClient);
    }


    private static void getData(ZkClient zkClient) {
        //获取节点数据
        String o = zkClient.readData("/test");
        System.out.println(o);
        //获取所有子节点的数据
        zkClient.getChildren("/test").stream().forEach(childPath->{
            String childData = zkClient.readData("/test/" + childPath);
            System.out.println(childData);
        });
    }

    //递归删除
    private static void delete(ZkClient zkClient) {
        boolean b = zkClient.deleteRecursive("/test");
        System.out.println(b);
    }

//    private static void create(ZkClient zkClient) {
//        zkClient.create("/test/dd", CreateMode.PERSISTENT_SEQUENTIAL);
//    }

    private static void create(ZkClient zkClient,String data) {
        zkClient.create("/test/dd", data, CreateMode.PERSISTENT_SEQUENTIAL);
    }

}
