package com.zongze.lock;

import com.zongze.core.ZookeeperClient;
import com.zongze.lock.Lock;
import com.zongze.model.LockInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Create By xzz on 2019/12/23
 */
@Component
public class ReentrantLock implements Lock {

    @Autowired
    private ZookeeperClient zkClient;

    /**
     * 存储锁信息
     * @param:
     * @return:
     */
    private ThreadLocal<LockInfo> threadLocal = new InheritableThreadLocal<>();

    /**
     * 将争抢锁的线程封装成LockInfo
     *
     * @param:
     * @return:
     */
    private LockInfo createZkLock(String path) throws InterruptedException, IOException, KeeperException {
        return new LockInfo(zkClient.createNode(path));
    }


    @Override
    public boolean lock(String lockName) throws InterruptedException, IOException, KeeperException {
        LockInfo lockInfo = createZkLock(lockName);
        threadLocal.set(lockInfo);
        List<String> allChildPath = zkClient.getAllChild();
        if (!StringUtils.equals(allChildPath.get(0), lockInfo.getPath())) {
            LockInfo active = zkClient.isActive(allChildPath.get(allChildPath.indexOf(lockInfo.getPath()) - 1), lockInfo);
            if (active.isActive()) {
                return true;
            }
        }
        return true;
    }

    @Override
    public void unLock() throws InterruptedException, IOException, KeeperException {
        zkClient.unLock(threadLocal.get().getPath());
    }
}
