package com.zongze.core;
import com.zongze.config.ZKLockConfig;
import com.zongze.model.ZKLockInfo;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * 操作zookeeper的客户端实现
 *
 * Create By xzz on 2019/12/23
 */
public class ZookeeperClient {

    private ZKLockConfig zkLockConfig;

    private ZooKeeper zooKeeper;


    /**
     * 创建临时序列节点
     *
     * @param:
     * @return:
     */
    public String initNode(String path, CreateMode createMode) throws KeeperException, InterruptedException {
        return createNode(zkLockConfig.getRootPath() + zkLockConfig.getSeparate() + path, createMode);
    }


    /**
     * 创建节点
     *
     * @param:
     * @return:
     */
    public String createNode(String path, CreateMode createMode) throws KeeperException, InterruptedException {
        return zooKeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
    }

    /**
     * 判断节点是否存在
     *
     * @param:
     * @return:
     */
    public boolean existNode(String path) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, false) == null ? false : true;
    }


    /**
     * 获取子节点
     */
    public List<String> getAllChild() throws KeeperException, InterruptedException {

        return zooKeeper.getChildren(zkLockConfig.getRootPath(), false)
                .stream()
                .sorted()
                .map(node -> node = zkLockConfig.getRootPath() + zkLockConfig.getSeparate() + node)
                .collect(Collectors.toList());
    }


    /**
     * 删除节点，触发监听，唤醒下一个线程
     */
    public void unLock(String path) throws KeeperException, InterruptedException {
        zooKeeper.delete(path, -1); //-1表示删除所有版本
        zooKeeper.close();
    }


    /**
     * 如果当前是第一个节点的话，就激活当前节点
     *
     * @param:
     * @return:
     */
    public ZKLockInfo tryActive(ZKLockInfo lockInfo) throws InterruptedException, KeeperException {
        List<String> childList = getAllChild();
        if (childList.get(0).equals(lockInfo.getPath())) {
            lockInfo.setActive(true);
        } else {
            lockInfo.setPreNode(childList.get(childList.indexOf(lockInfo.getPath()) - 1));
        }
        return lockInfo;
    }


    /**
     * 监听上一个序列节点，阻塞线程，直到上一个节点被删除
     *
     * @param:
     * @return:
     */
    public ZKLockInfo tryLock(ZKLockInfo lockInfo) throws KeeperException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZKLockInfo finalLockInfo = lockInfo;
        Stat stat = zooKeeper.exists(lockInfo.getPreNode(), new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType().equals(Event.EventType.NodeDeleted)) {
                    try {
                        List<String> childList = getAllChild();
                        if (childList.get(0).equals(finalLockInfo.getPath())) {
                            finalLockInfo.setActive(true);
                        } else {
                            finalLockInfo.setPreNode(childList.get(childList.indexOf(finalLockInfo.getPath()) - 1));
                        }
                        countDownLatch.countDown();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        if (ObjectUtils.isEmpty(stat)) {
            lockInfo = tryActive(lockInfo);
            if (lockInfo.isActive()) {
                return lockInfo;
            }
        }
        countDownLatch.await();
        if (!lockInfo.isActive()) {
            tryLock(lockInfo);
        }
        return lockInfo;
    }



    public ZookeeperClient(ZooKeeper zooKeeper, ZKLockConfig zkLockConfig) {
        this.zkLockConfig = zkLockConfig;
        this.zooKeeper = zooKeeper;
    }

}
