package com.homework.order.listener;

import com.homework.config.CommonConfig;
import com.homework.entity.User;
import com.homework.order.service.OrderService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@RocketMQMessageListener(consumerGroup = "my-group",topic = CommonConfig.ACCOUNT_TOPIC)
public class UserListener implements RocketMQListener<User> {

    Logger logger = LoggerFactory.getLogger(UserListener.class);

    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(User user) {
        logger.info("========= consumer user rocketMq message ==========");
        orderService.updateOrderUser(user);
    }
}
