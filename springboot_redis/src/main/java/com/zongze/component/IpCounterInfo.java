package com.zongze.component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create By xzz on 2019/12/19
 */
public class IpCounterInfo {

    private String ip;

    /**
     * 实际统计ip访问次数的计数器
     *
     * @param:
     * @return:
     */
    private AtomicInteger requestCounter = new AtomicInteger(0);


    /**
     * 限制的次数
     *
     * @param:
     * @return:
     */
    private final int limit = 10;


    /**
     * 限制的时间差
     *
     * @param:
     * @return:
     */
    private final long interval = 60000;


    /**
     * 限制结束时间戳
     *
     * @param:
     * @return:
     */
    private long endTime;


    /**
     * 通过限流返回true
     *
     * @param:
     * @return:
     */
    public boolean isAllow() {
        if (System.currentTimeMillis() >= endTime) {
            this.endTime = System.currentTimeMillis() + interval;
            requestCounter.set(1);
            return true;
        }
        if (requestCounter.get() >= limit) {
            return false;
        }
        increment();
        return true;
    }



    /**
     * 增加计数
     *
     * @param:
     * @return:
     */
    public int increment() {
        return requestCounter.incrementAndGet();
    }



    /**
     * 获取ip计数器
     *
     * @param:
     * @return:
     */
    public static IpCounterInfo getCounter(String ip, ConcurrentHashMap<String, IpCounterInfo> counterContext) {
        IpCounterInfo counter;
        synchronized (IpCounterInfo.class) {
            if ((counter = counterContext.get(ip)) == null) {
                counterContext.put(ip, counter = new IpCounterInfo(ip));
            }
        }
        return counter;
    }


    public IpCounterInfo(String ip) {
        this.ip = ip;
        this.endTime = System.currentTimeMillis() + interval;
    }
}
