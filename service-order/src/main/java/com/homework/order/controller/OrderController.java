package com.homework.order.controller;

import com.homework.entity.Order;
import com.homework.entity.User;
import com.homework.order.feignClient.UserFeignClient;
import com.homework.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/createOrder")
    public String createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return "success";
    }
}
