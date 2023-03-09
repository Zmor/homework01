package com.homework.controller;


import com.homework.entity.User;
import com.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getInfo")
    public User getInfo(Integer userId) {
        return userService.queryInfo(userId);
    }


    @PostMapping("/insOrUpd")
    public String insOrUpd(@RequestBody User user) {
        if (user.getId() != null) {
            userService.update(user);
        } else {
            userService.insert(user);
        }
        return "";
    }

}
