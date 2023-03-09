package com.homework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homework.config.CommonConfig;
import com.homework.entity.User;
import com.homework.mapper.UserMapper;
import com.homework.service.UserService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public User queryInfo(Integer userId) {
        return baseMapper.selectById(userId);
    }

    @Override
    public User insert(User user) {
        baseMapper.insert(user);
        rocketMQTemplate.convertAndSend(CommonConfig.ACCOUNT_TOPIC,user);
        return user;
    }

    @Override
    public Integer update(User user) {
        Integer i = baseMapper.updateById(user);
        rocketMQTemplate.convertAndSend(CommonConfig.ACCOUNT_TOPIC,user);
        return i;
    }
}
