package com.homework.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 订单服务账户实体
 */
@TableName(value = "t_user_bak")
public class UserBak implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
