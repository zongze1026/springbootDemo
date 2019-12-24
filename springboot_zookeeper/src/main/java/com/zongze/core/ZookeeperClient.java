package com.zongze.core;

import com.zongze.config.ZlockConfig;
import com.zongze.model.ZlockInfo;
import org.apache.zookeeper.*;
import org.springframework.util.ObjectUtils;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * 操作zookeeper的客户端
 *
 * Create By xzz on 2019/12/23
 */
public class ZookeeperClient {

    private ZlockConfig zlockConfig;

    private ZooKeeper zooKeeper;

    private void connect() throws IOException {
        if (ObjectUtils.isEmpty(zooKeeper)) {
            synchronized (ZookeeperClient.class) {
                if (ObjectUtils.isEmpty(zooKeeper)) {
                    zooKeeper = new ZooKeeper(zlockConfig.getHost(), zlockConfig.getSessionTimeout(), new Watcher() {
                        @Override
                        public void process(WatchedEvent watchedEvent) {
                            System.out.println("成功连接zookeeper");
                        }
                    });
                }
            }
        }
    }


    /**
     * 创建临时序列节点
     *
     * @param:
     * @return:
     */
    public String createNode(String path) throws IOException, KeeperException, InterruptedException {
        if (ObjectUtils.isEmpty(zooKeeper)) {
            connect();
        }
        return zooKeeper.create(zlockConfig.getParentPath() + zlockConfig.getSEPARATE() + path,
                null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }


    /**
     * 获取子节点
     */
    public List<String> getAllChild() throws KeeperException, InterruptedException, IOException {
        if (ObjectUtils.isEmpty(zooKeeper)) {
            connect();
        }
        return zooKeeper.getChildren(zlockConfig.getParentPath(), false)
                .stream()
                .sorted()
                .map(node -> node = zlockConfig.getParentPath() + zlockConfig.getSEPARATE() + node)
                .collect(Collectors.toList());
    }


    /**
     * 删除节点，触发监听，唤醒下一个线程
     */
    public void unLock(String path) throws KeeperException, InterruptedException, IOException {
        if (ObjectUtils.isEmpty(zooKeeper)) {
            connect();
        }
        zooKeeper.delete(path, -1); //-1表示删除所有版本
    }


    /**
     * 监听上一个序列节点，阻塞线程，直到上一个节点被删除
     *
     * @param:
     * @return:
     */
    public ZlockInfo tryLock(String preNode, ZlockInfo lockInfo) throws KeeperException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper.exists(preNode, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType().equals(Event.EventType.NodeDeleted)) {
                    try {
                        List<String> childList = getAllChild();
                        if (childList.get(0).equals(lockInfo.getPath())) {
                            lockInfo.setActive(true);
                        } else {
                            lockInfo.setPreNode(childList.get(childList.indexOf(lockInfo.getPath()) - 1));
                        }
                        countDownLatch.countDown();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        countDownLatch.await();
        //todo 考虑线程是否中断
        if (!lockInfo.isActive()) {
            tryLock(lockInfo.getPreNode(), lockInfo);
        }
        return lockInfo;
    }

    public ZookeeperClient(ZlockConfig zlockConfig) {
        this.zlockConfig = zlockConfig;
    }
}
