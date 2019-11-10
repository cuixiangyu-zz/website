package com.cxy.website.service.sys;

import com.cxy.website.model.sys.SysMenu;
import com.cxy.website.model.sys.SysRole;

import java.util.List;
import java.util.Map;

/**
 *@Description 角色管理service
 *@Author 侯森林
 *@Date 2019-9-18
 */
public interface RoleService {
    /**
     *@Description 分页查询角色信息
     *@Param role 角色名称 pageSize 条数 pageNo 页数
     *@Return  List<Role>
     *@Author 侯森林
     *@Date 2019-9-18
     */
    List<SysRole> findRole(String roleName, Integer page, Integer rows) ;
    /**
     *@Description 新增角色
     *@Param role 角色信息
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void insertRole(SysRole role);
    /**
     *@Description 根据角色id查询角色信息
     *@Param id 角色id
     *@Return Role 角色信息
     *@Author 侯森林
     *@Date 2019-9-18
     */
    SysRole selectRoleByRoleId(Integer id);
    /**
     *@Description 根据角色id删除角色
     *@Param id 角色id
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void deleteRole(Integer roleId);

    /**
     *@Description 角色数量信息
     *@Param role 角色名称
     *@Return  Map<String, Integer> 角色数量信息
     *@Author 侯森林
     *@Date 2019-9-18
     */
    Map<String, Integer> countRoleList(String role);
    /**
     *@Description 根据角色名称查询角色信息
     *@Param role 角色名称
     *@Return Role 角色信息
     *@Author 侯森林
     *@Date 2019-9-18
     */
    SysRole findByRoleName(String roleName);
    /**
     *@Description 修改角色
     *@Param role 角色信息
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void updateRoleById(SysRole role);

    /**
     *@Description 获取角色拥有的菜单
     *@Param  id 角色id
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    List<SysMenu> getMenuList(Integer id);

    /**
     *@Description 获取角色id删除菜单
     *@Param  id 角色id
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void deleteRoleMenu(Integer roleId);

    /**
     *@Description 给角色分配菜单权限
     *@Param  id 角色id menuIdList 菜单id
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void insertRoleMenu(Integer roleId, List<Integer> menuIdList);
}
