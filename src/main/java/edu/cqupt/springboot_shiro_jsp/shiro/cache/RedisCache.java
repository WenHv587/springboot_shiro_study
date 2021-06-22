package edu.cqupt.springboot_shiro_jsp.shiro.cache;

import edu.cqupt.springboot_shiro_jsp.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

/**
 * @author LWenH
 * @create 2021/6/20 - 23:07
 * <p>
 * 自定义redis缓存的实现
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    private String cacheName;

    public RedisCache() {
    }

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

//    private RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");

//    @Override
//    public V get(K k) throws CacheException {
//        System.out.println("get key:" + k);
//        return (V) redisTemplate.opsForHash().get(this.cacheName, k.toString());
//    }
//
//    @Override
//    public V put(K k, V v) throws CacheException {
//        System.out.println("redis:" + getRedisTemplate());
//        System.out.println("put key:" + k + ", value:" + v);
//        redisTemplate.opsForHash().put(this.cacheName, k.toString(), v);
//        return null;
//    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("get key:" + k);
        return (V) getRedisTemplate().opsForHash().get(this.cacheName, k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("redis:" + getRedisTemplate());
        System.out.println("put key:" + k + ", value:" + v);
        getRedisTemplate().opsForHash().put(this.cacheName, k.toString(), v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
//        System.out.println("=============remove=============");
//        return (V) getRedisTemplate().opsForHash().delete(this.cacheName, k.toString());
        return null;
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("=============clear=============");
        getRedisTemplate().delete(this.cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }
}
