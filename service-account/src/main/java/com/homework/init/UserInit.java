package com.homework.init;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.homework.config.CommonConfig;
import com.homework.entity.User;
import com.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class UserInit implements ApplicationRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> userList = userService.list();
        if(CollectionUtils.isEmpty(userList)){
            return;
        }
        // 将用户放入redis中
        Map<String, String> map = userList.stream().collect(Collectors.toMap(
                e -> CommonConfig.USER_KEY+e.getId().toString(),
                e -> JSON.toJSONString(e)
        ));
        redisTemplate.opsForValue().multiSetIfAbsent(map);

    }
}
