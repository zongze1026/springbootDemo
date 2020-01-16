package com.zongze.lock;

import com.zongze.core.ZookeeperClient;
import com.zongze.model.ZKLockInfo;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

/**
 * Create By xzz on 2019/12/23
 */
public class ZKReentrantLock implements ZKLock {

    private ZookeeperClient zkClient;

    /**
     * 存储锁信息
     *
     * @param:
     * @return:
     */
    private ThreadLocal<ZKLockInfo> threadLocal = new InheritableThreadLocal<>();

    /**
     * 将争抢锁的线程封装成LockInfo
     *
     * @param:
     * @return:
     */
    private ZKLockInfo createZkLock(String path) throws InterruptedException, KeeperException {
        return new ZKLockInfo(zkClient.initNode(path, CreateMode.EPHEMERAL_SEQUENTIAL));
    }


    @Override
    public boolean lock(String lockName) throws InterruptedException, KeeperException {
        ZKLockInfo lockInfo = createZkLock(lockName);
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

    public ZKReentrantLock(ZookeeperClient zkClient) {
        this.zkClient = zkClient;
    }
}
