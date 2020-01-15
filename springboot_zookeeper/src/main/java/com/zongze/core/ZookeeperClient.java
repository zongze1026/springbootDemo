package com.zongze.core;
import com.zongze.config.ZlockConfig;
import com.zongze.model.ZlockInfo;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * 操作zookeeper的客户端实现
 * <p>
 * Create By xzz on 2019/12/23
 */
public class ZookeeperClient {

    private ZlockConfig zlockConfig;

    private ZooKeeper zooKeeper;


    /**
     * 创建临时序列节点
     *
     * @param:
     * @return:
     */
    public String initNode(String path, CreateMode createMode) throws KeeperException, InterruptedException {
        return createNode(zlockConfig.getRootPath() + zlockConfig.getSeparate() + path, createMode);
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

        return zooKeeper.getChildren(zlockConfig.getRootPath(), false)
                .stream()
                .sorted()
                .map(node -> node = zlockConfig.getRootPath() + zlockConfig.getSeparate() + node)
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
    public ZlockInfo tryActive(ZlockInfo lockInfo) throws InterruptedException, KeeperException {
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
    public ZlockInfo tryLock(ZlockInfo lockInfo) throws KeeperException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZlockInfo finalLockInfo = lockInfo;
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



    public ZookeeperClient(ZooKeeper zooKeeper, ZlockConfig zlockConfig) {
        this.zlockConfig = zlockConfig;
        this.zooKeeper = zooKeeper;
    }

}
