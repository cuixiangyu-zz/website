package com.cxy.website.model.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *@Description 用户实体类
 *@Author 侯森林
 *@Date 2019-9-18
 */
public class SysUser implements Serializable {

    private Integer id;//用户id

    private String email;//邮箱

    private Integer isDel;//是否删除 0：未删除 1：删除

    private String name;//姓名

    private String password;//密码

    private String salt;//密码盐

    private Byte state;//状态 0：整除 1：禁用

    private String tel;//电话

    private String userName;//用户名

    private Integer age;//年龄

    private Integer sex;//性别 0：男 1：女

    private String ipLimit;//用户ip

    private Date createTime;//创建时间

    private Integer creater;//创建人

    private String remark;//备注

    private String credentialsSalt;//md5加密盐由userName+salt组成

    List<SysRole> sysUserRoleList;

    List<SysMenu> sysUserMenuList;

    public List<SysRole> getSysUserRoleList() {
        return sysUserRoleList;
    }

    public void setSysUserRoleList(List<SysRole> sysUserRoleList) {
        this.sysUserRoleList = sysUserRoleList;
    }

    public List<SysMenu> getSysUserMenuList() {
        return sysUserMenuList;
    }

    public void setSysUserMenuList(List<SysMenu> sysUserMenuList) {
        this.sysUserMenuList = sysUserMenuList;
    }

    private static final long serialVersionUID = 1L;


    public String getCredentialsSalt() {
        return this.userName+this.salt;
    }

    public void setCredentialsSalt(String credentialsSalt) {
        this.credentialsSalt = credentialsSalt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIpLimit() {
        return ipLimit;
    }

    public void setIpLimit(String ipLimit) {
        this.ipLimit = ipLimit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}