package com.zongze.test;
import com.alibaba.fastjson.JSON;
import com.zongze.entity.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Create By xzz on 2018/12/28
 */
public class EhcacheTest {


    public static void main(String[] args) throws Exception {

        //加载配置文件获取缓存管理器
        CacheManager cacheManager = CacheManager.create();

        //根据名称获取缓存
        Cache cache = cacheManager.getCache("cacheTest");

        //创建元素,并设置缓存过期时间为5秒
        User user = new User();
        user.setUserName("zhangsan");
        user.setAge(18);
        Element element2 = new Element("user", user);
        cache.put(element2);
        //休眠3秒
        Thread.currentThread().sleep(1000*3);

        Element element = cache.get("user");
        System.out.println(JSON.toJSONString(element.getObjectValue()));

        //再次休眠3秒,缓存消失
        Thread.currentThread().sleep(1000*3);

        //虽然缓存已经过期；但是size没有重置，这个时ehcache框架存在的问题
        System.out.println(cache.getSize());  // 1

        Element element3 = cache.get("user");
        System.out.println(element3);  //null



    }


}
