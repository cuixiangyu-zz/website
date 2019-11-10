package com.cxy.website.service.sys;

import com.cxy.website.model.sys.SysMenu;
import com.cxy.website.model.sys.SysRole;
import com.cxy.website.model.sys.SysUser;

import java.util.List;
import java.util.Map;

/**
 *@Description 用户管理service
 *@Author 侯森林
 *@Date 2019-9-18
 */

public interface SysUserService {
    /**
     *@Description 根据用户名查询用户信息
     *@Param userName
     *@Return UserSectionsMapper
     *@Author 侯森林
     *@Date 2019-9-9
     */
    SysUser findByUserName(String userName);
    /**
     *@Description 分页条件查询用户信息
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    List<SysUser> findUser(String userName, Integer sex, Integer age, String beginDate, String endDate, Integer page, Integer rows);
    /**
     *@Description 返回分页条件查询用户数量信息
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    Map<String, Integer> countUserList(String userName, Integer sex, Integer age, String beginDate, String endDate);
    /**
     *@Description 根据用户id查询用户信息
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    SysUser selectByPrimaryKey(Integer userId);
    /**
     *@Description 新增用户
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void insertSelective(SysUser user);
    /**
     *@Description 修改用户信息
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void updateUser(SysUser user);

    /**
     *@Description 修改密码
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void changePassword(SysUser user);
    void updatePwd(SysUser user);
    /**
     *@Description 删除用户
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void deleteUserById(Integer userId);
    /**
     *@Description 获取角色列表
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    List<SysRole>findRoleList(Integer userId);

    /**
     *@Description 根据用户id删除用户所有角色
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void  deleteAllUserRoleByUserId(Integer userId);
    /**
     *@Description 用户分配角色
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void insertUserRole(Integer userId, List<Integer> list);
    /**
     *@Description 获取用户菜单
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    List<SysMenu> findMenuByUserId(Integer userId);







}
