package com.homework.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.homework.entity.Order;
import com.homework.entity.User;

public interface OrderService extends IService<Order> {

    /**
     * 创建订单
     * @param order
     */
    void createOrder(Order order);


    /**
     * 修改订单服务的用户信息
     * @param user
     * @return
     */
    Boolean updateOrderUser(User user);
}
