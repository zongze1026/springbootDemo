package com.zongze.security.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zongze.security.component.entity.AuthUser;
import com.zongze.security.component.entity.ManagerRole;
import com.zongze.util.SnowflakeIdWorker;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2020/9/10 17:29
 * @Created by xzz
 */
@Component
@EnableScheduling
public class UserTokenOperationHandler {
    private SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(10, 10);
    private Map<String, List<String>> tokens = new ConcurrentHashMap();
    private static final String TOKEN_PREFIX = "headManager:token:";
    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 存储或者刷新redis中token
     *
     * @param token
     * @param expire
     * @param authUser
     */
    public String flushToken(String token, long expire, AuthUser authUser) {
        if (StringUtils.isEmpty(token)) {
            token = String.valueOf(snowflakeIdWorker.nextId());
            addTokenToMap(token, authUser);
            redisTemplate.opsForValue().set(TOKEN_PREFIX + token, JSON.toJSONString(authUser), expire, TimeUnit.MINUTES);
        } else {
            redisTemplate.expire(TOKEN_PREFIX + token, expire, TimeUnit.MINUTES);
        }
        return token;
    }


    /**
     * 添加token
     *
     * @param token
     * @param authUser
     * @return void
     */
    private void addTokenToMap(String token, AuthUser authUser) {
        List<ManagerRole> roles = authUser.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            for (ManagerRole managerRole : roles) {
                String roleId = String.valueOf(managerRole.getId());
                List<String> tokenList = tokens.get(roleId);
                if (CollectionUtils.isEmpty(tokenList)) {
                    tokenList = new ArrayList<>();
                    tokens.put(roleId, tokenList);
                }
                tokenList.add(token);
            }
        }
    }


    /**
     * 获取用户信息
     *
     * @param token
     */
    public AuthUser getUserDetail(String token) {
        AuthUser authUser = JSONObject.parseObject(redisTemplate.opsForValue().get(TOKEN_PREFIX + token), AuthUser.class);
        if (null == authUser || CollectionUtils.isEmpty(authUser.getRoles())) {
            return null;
        }
        return hashTokenForMap(token, authUser) ? authUser : null;
    }


    /**
     * 验证map中是否存在token
     *
     * @param token
     * @param authUser
     * @return boolean
     */
    private boolean hashTokenForMap(String token, AuthUser authUser) {
        boolean match = false;
        List<ManagerRole> roles = authUser.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            for (ManagerRole role : roles) {
                List<String> tokens = this.tokens.get(String.valueOf(role.getId()));
                match = tokens.stream().anyMatch(t -> token.equals(t));
                if (match) {
                    break;
                }
            }
        }
        return match;
    }


    /**
     * 删除用户token
     *
     * @param authUser
     * @param token
     */
    public void delTokenForMap(AuthUser authUser, String token) {
        List<ManagerRole> roles = authUser.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            roles.stream().forEach(managerRole -> delTokenForMap(String.valueOf(managerRole.getId()), token));
        }
        redisTemplate.delete(TOKEN_PREFIX + token);
    }


    /**
     * 删除指定的token
     *
     * @param roleId
     * @param token
     */
    public void delTokenForMap(String roleId, String token) {
        if(StringUtils.isEmpty(token)){
            tokens.remove(roleId);
        }else{
            List<String> tokens = this.tokens.get(roleId);
            if (!CollectionUtils.isEmpty(tokens)) {
                Iterator<String> iterator = tokens.iterator();
                while (iterator.hasNext()) {
                    String serverToken = iterator.next();
                    if (token.equals(serverToken)) {
                        iterator.remove();
                    }
                }
            }
        }
    }


    /**
     * 清理过期的token
     *
     * @param
     * @return void
     */
    @Scheduled(cron = "0 15 0 * * ?")
    private void clearMap() {
        Set<String> keys = redisTemplate.keys(TOKEN_PREFIX + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            Set<Map.Entry<String, List<String>>> entries = tokens.entrySet();
            for (Map.Entry<String, List<String>> entry : entries) {
                Iterator<String> iterator = entry.getValue().iterator();
                while (iterator.hasNext()) {
                    String token = iterator.next();
                    boolean match = keys.stream().anyMatch(redisToken -> redisToken.equals(TOKEN_PREFIX + token));
                    if (!match) {
                        iterator.remove();
                    }
                }
            }
        }
    }


}
