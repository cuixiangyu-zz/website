package com.cxy.website.model.sys;

import java.io.Serializable;
import java.util.Date;

/**
 *@Description 角色实体类
 *@Author 侯森林
 *@Date 2019-9-18
 */
public class SysRole implements Serializable {

    private Integer id;//角色id

    private String roleName;//角色名称

    private String description;//描述

    private Date createTime;//创建时间

    private String remark;//备注

    private Integer userId;//用户id

    private Integer isDel;//是否删除 0：未删除 1：删除

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}