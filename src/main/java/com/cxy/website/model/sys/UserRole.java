package com.cxy.website.model.sys;

import java.io.Serializable;

/**
 *@Description 用户-角色中间表实体类
 *@Author 侯森林
 *@Date 2019-9-18
 */
public class UserRole implements Serializable {

    private Integer id;//id

    private Integer roleId;//角色id

    private Integer userId;//用户id

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}