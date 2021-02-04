package com.xxcw.allowance.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class BaseRedisService {
    //key的前缀，用 项目名_ 的方式定义前缀 如：myapp_
    @Value("${redis.key.prefix}")
    private String KEY_PREFIX;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String set(String key, String value, long timeout) {
        String newKey = KEY_PREFIX + key;
        redisTemplate.opsForValue().set(newKey, value, timeout, TimeUnit.SECONDS);
        return newKey;
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(KEY_PREFIX + key, value);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(KEY_PREFIX + key);
    }

    public boolean expire(String key, long timeout) {
        return redisTemplate.expire(KEY_PREFIX + key, timeout, TimeUnit.SECONDS);
    }

    public String getOneKeyLike(String key) {
        Set<String> keys = redisTemplate.keys(KEY_PREFIX + key + "*");
        if (keys.isEmpty()){
            return null;
        }
        return keys.toArray()[0].toString().substring(KEY_PREFIX.length());
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(KEY_PREFIX + key);
    }

    public void removeByKey(String key) {
        redisTemplate.delete(KEY_PREFIX + key);
    }

    public void removeByKeyLike(String key) {
        Set<String> keys = redisTemplate.keys(KEY_PREFIX + key + "*");
        log.info(Arrays.toString(keys.toArray()));
        redisTemplate.delete(keys);
    }
}
