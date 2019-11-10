package com.cxy.website.service.sys.impl;

import com.cxy.website.dao.sys.SysMenuMapper;
import com.cxy.website.dao.sys.SysRoleMapper;
import com.cxy.website.dao.sys.SysRoleMenuMapper;
import com.cxy.website.model.sys.SysMenu;
import com.cxy.website.model.sys.SysRole;
import com.cxy.website.model.sys.SysRoleMenu;
import com.cxy.website.service.sys.RoleService;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Description 角色管理service实现类
 *@Author 侯森林
 *@Date 2019-9-18
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    SysMenuMapper sysMenuMapper;

    /**
     *@Description 分页查询角色信息
     *@Param role 角色名称 pageSize 条数 pageNo 页数
     *@Return  List<Role>
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public List<SysRole> findRole(String roleName, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return sysRoleMapper.findRole(roleName);
    }

    /**
     *@Description 新增角色
     *@Param role 角色信息
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void insertRole(SysRole role) {
        sysRoleMapper.insertSelective(role);
    }

    /**
     *@Description 根据角色id查询角色信息
     *@Param id 角色id
     *@Return Role 角色信息
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public SysRole selectRoleByRoleId(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    /**
     *@Description 根据角色id删除角色
     *@Param id 角色id
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void deleteRole(Integer roleId) {
        SysRole role=new SysRole();
        role.setId(roleId);
        role.setIsDel(1);
        sysRoleMapper.updateByPrimaryKeySelective(role);
        sysRoleMenuMapper.deleteMenuByRoleId(roleId);
    }

    /**
     *@Description 角色数量信息
     *@Param role 角色名称
     *@Return  Map<String, Integer> 角色数量信息
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public Map<String, Integer> countRoleList(String role) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        Integer totalRows = sysRoleMapper.countRoleList(role);
        map.put("totalRows",totalRows);
        return map;
    }

    /**
     *@Description 根据角色名称查询角色信息
     *@Param role 角色名称
     *@Return Role 角色信息
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public SysRole findByRoleName(String roleName) {
        return sysRoleMapper.findByRoleName(roleName);
    }

    /**
     *@Description 修改角色
     *@Param role 角色信息
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void updateRoleById(SysRole role) {
     sysRoleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     *@Description 获取角色拥有的菜单
     *@Param  id 角色id
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public List<SysMenu> getMenuList(Integer id) {
        List<Integer> menuIdList= sysRoleMenuMapper.selectMenuIdList(id);
        List<SysMenu> menuList= sysMenuMapper.getAllMenu();
        if(menuIdList.size()==0){
            return  menuList;
        }else {
            for(Integer menuId:menuIdList){
                for (SysMenu menu:menuList){
                    if(menuId.equals(menu.getId())){
                        menu.setRoleId(id);
                        continue;
                    }
                    continue;
                }
            }
            return menuList;
            }
    }

    /**
     *@Description 获取角色id删除菜单
     *@Param  id 角色id
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void deleteRoleMenu(Integer roleId) {

        sysRoleMenuMapper.deleteMenuByRoleId(roleId);

    }

    /**
     *@Description 给角色分配菜单权限
     *@Param  id 角色id menuIdList 菜单id
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void insertRoleMenu(Integer roleId, List<Integer> menuIdList) {
        List<SysRoleMenu> sysRoleMenuList=new ArrayList<>();
        for(Integer menuId:menuIdList){
            SysRoleMenu sysRoleMenu=new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenuList.add(sysRoleMenu);
        }
       sysRoleMenuMapper.insertRoleMenu(sysRoleMenuList);
    }

}
