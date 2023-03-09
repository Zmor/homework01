package com.homework.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 账户实体
 */
@TableName(value = "t_user")
public class User implements Serializable {

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
