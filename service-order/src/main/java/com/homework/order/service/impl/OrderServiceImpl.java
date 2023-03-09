package com.homework.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homework.config.CommonConfig;
import com.homework.entity.Order;
import com.homework.entity.User;
import com.homework.entity.UserBak;
import com.homework.order.feignClient.UserFeignClient;
import com.homework.order.mapper.OrderMapper;
import com.homework.order.mapper.UserBakMapper;
import com.homework.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private UserBakMapper userBakMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    @Resource
    private StringRedisTemplate redisTemplate;


    @Override
    public void createOrder(Order order) {
        User user = getUser(order.getUserId());
        order.setUserName(user.getName());
        save(order);
    }

    @Override
    public Boolean updateOrderUser(User user) {
        if (null == user) {
            return Boolean.TRUE;
        }
        UserBak userBak = new UserBak();
        userBak.setId(user.getId());
        userBak.setName(user.getName());
        UserBak userBakN = userBakMapper.selectById(userBak.getId());
        if (null == userBakN) {
            userBakMapper.insert(userBak);
        } else {
            userBakMapper.updateById(userBak);
        }
        return Boolean.TRUE;
    }

    private User getUser(Integer userId) {
        User user = null;
        // userName通过redis获取
        String key = CommonConfig.USER_KEY+userId;

        String userJson = redisTemplate.opsForValue().get(key);
        if(userJson == null){
            // 用户不存在 从本地数据库中查询
            UserBak userBak = userBakMapper.selectById(userId);
            if(userBak==null){
                // 本地数据库不存在，则从远程数据库中查询
                user = userFeignClient.queryInfo(userId);
            } else {
                user = new User();
                BeanUtils.copyProperties(userBak, user);
            }
            if (null != user) {
                redisTemplate.opsForValue().setIfAbsent(CommonConfig.USER_KEY+user.getId().toString(),JSON.toJSONString(user));
            }
        } else {
            user = JSON.parseObject(userJson,User.class);
        }

        if(user ==null){
            throw new IllegalArgumentException("用户不存在");
        }
        return user;
    }
}
