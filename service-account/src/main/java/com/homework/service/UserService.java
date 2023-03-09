package com.homework.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.homework.entity.User;

public interface UserService extends IService<User> {



    /**
     * 查询用户详情
     * @param userId
     * @return
     */
    User queryInfo(Integer userId);

    /**
     * 新增用户
     * @param user
     * @return
     */
    User insert(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    Integer update(User user);
}
