package com.henvealf.learn.ehcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.Status;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.junit.Test;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-12-17
 */
public class EhcacheTest {

    @Test
    public void test() {
        String cacheName = "test";

        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache(cacheName,
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, User.class, ResourcePoolsBuilder.heap(100).build()))
                .build();

        cacheManager.init();

        Cache<String, User> cache = cacheManager.getCache(cacheName, String.class, User.class);

        cache.put("0001", new User("hello", 12, 15));

        User user = cache.get("0001");
        System.out.println("user.getName(): " + user.getName());
        System.out.println("user.getAge(): " + user.getAge());
        System.out.println("user.getGrade(): " + user.getGrade());

        cacheManager.close();
    }

}
