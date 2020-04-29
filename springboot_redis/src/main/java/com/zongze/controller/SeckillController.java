package com.zongze.controller;

import com.alibaba.fastjson.JSON;
import com.zongze.entity.ResultResp;
import com.zongze.model.CacheKey;
import com.zongze.model.User;
import com.zongze.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By xzz on 2019/4/4
 */
@Api("秒杀测试")
@RestController
@RequestMapping("sckill")
public class SeckillController {


    private static Logger logger = LoggerFactory.getLogger(SeckillController.class);

    @ApiOperation("秒杀商品放入redis")
    @PostMapping("product")
    public ResultResp save() {
        RedisUtil.set(CacheKey.SCKILL_PRODUCT.getKey("count"), 100, CacheKey.SCKILL_PRODUCT.getCacheTime());
        RedisUtil.set(CacheKey.QR_PERSISTENCE.getKey("count"), 0, CacheKey.QR_PERSISTENCE.getCacheTime());
        return ResultResp.succ();
    }


    @ApiOperation("秒杀接口")
    @PostMapping("start")
    public ResultResp start() {
        return getResultResp();
    }

    private ResultResp getResultResp() {
//        synchronized (SeckillController.class) {
            long count = RedisUtil.increment(CacheKey.SCKILL_PRODUCT.getKey("count"), -1);
            if (count > 0) {
                User user = new User("张三", 15l, "大黄", "123456");
                user.setUserName(String.valueOf(System.currentTimeMillis()));
                user.setAge(count);
                RedisUtil.increment(CacheKey.QR_PERSISTENCE.getKey("order"),1);
                logger.info("秒杀信息[{}]；还剩余的商品：{}", JSON.toJSONString(user), count);
                return ResultResp.succ();
            }
            return ResultResp.fail("秒杀结束");
        }
//    }


}



