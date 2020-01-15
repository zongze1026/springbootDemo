package com.zongze.lock;

import com.zongze.core.ZookeeperClient;
import com.zongze.model.ZlockInfo;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

/**
 * Create By xzz on 2019/12/23
 */
public class ReentrantLock implements Lock {

    private ZookeeperClient zkClient;

    /**
     * 存储锁信息
     *
     * @param:
     * @return:
     */
    private ThreadLocal<ZlockInfo> threadLocal = new InheritableThreadLocal<>();

    /**
     * 将争抢锁的线程封装成LockInfo
     *
     * @param:
     * @return:
     */
    private ZlockInfo createZkLock(String path) throws InterruptedException, KeeperException {
        return new ZlockInfo(zkClient.initNode(path, CreateMode.EPHEMERAL_SEQUENTIAL));
    }


    @Override
    public boolean lock(String lockName) throws InterruptedException, KeeperException {
        ZlockInfo lockInfo = createZkLock(lockName);
        threadLocal.set(lockInfo);
        lockInfo = zkClient.tryActive(lockInfo);
        if (lockInfo.isActive()) {
            return true;
        }
        zkClient.tryLock(lockInfo);
        return true;
    }

    @Override
    public void unLock() throws InterruptedException, KeeperException {
        zkClient.unLock(threadLocal.get().getPath());
    }

    public ReentrantLock(ZookeeperClient zkClient) {
        this.zkClient = zkClient;
    }
}
