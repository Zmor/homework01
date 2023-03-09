package com.homework.order.feignClient;

import com.homework.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "service-account")
public interface UserFeignClient {

    @GetMapping("/user/getInfo")
    User queryInfo(@RequestParam("userId") Integer userId);
}
