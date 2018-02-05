package com.ciyou.edu.config.redis

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.core.RedisTemplate

import java.lang.reflect.Method

/**
 * @Author C.
 * @Date 2018-02-04 22:32
 * Redis配置类
 * @EnableCaching注解来开启我们的项目支持缓存
 * 继承CachingConfigurerSupport重写keyGenerator()来自定义key
 */
@Configuration
@EnableCaching
class RedisConfiguration extends CachingConfigurerSupport{


    private static final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class)

    /**
     * 采用RedisCacheManager作为缓存管理器
     * @param redisTemplate
     * @return
     * SpringBoot项目启动时就会去找自定义配置的CacheManager对象并且自动应用到项目中
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        logger.info("生成RedisCacheManager缓存管理器...")
        return new RedisCacheManager(redisTemplate)
    }

    @Override
    KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            Object generate(Object target, Method method, Object... params) {
                //格式化缓存key字符串
                StringBuilder sb = new StringBuilder()
                //追加类名
                sb.append(target?.getClass()?.getName())
                //追加方法名
                sb.append(method?.getName())
                //遍历参数并且追加
                for(Object obj : params){
                    sb.append(obj.toString())
                }
                logger.info("调用Redis缓存Key:" + sb.toString())
                return sb.toString()
            }
        }
    }
}
