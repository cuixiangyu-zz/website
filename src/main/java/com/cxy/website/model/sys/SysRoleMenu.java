package com.cxy.website.model.sys;

import java.io.Serializable;

/**
 *@Description 角色-菜单中间表实体类
 *@Author 侯森林
 *@Date 2019-9-18
 */
public class SysRoleMenu implements Serializable {

    private Integer id;//id

    private Integer roleId;//角色id

    private Integer menuId;//菜单id

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

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

}