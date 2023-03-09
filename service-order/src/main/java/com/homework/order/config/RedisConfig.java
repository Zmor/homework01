package com.homework.order.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public StringRedisTemplate get(RedisConnectionFactory connectionFactory){
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        StringRedisSerializer serializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer(Object.class));

        return redisTemplate;
    }
}
