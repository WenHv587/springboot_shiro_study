package edu.cqupt.springboot_shiro_jsp.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author LWenH
 * @create 2021/6/20 - 23:03
 */
public class MyRedisCacheManager implements CacheManager {

    /**
     *
     * @param cacheName 认证或授权缓存的名称
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new RedisCache<>(cacheName);
    }
}
