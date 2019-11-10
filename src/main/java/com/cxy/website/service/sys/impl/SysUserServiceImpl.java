package com.cxy.website.service.sys.impl;

import com.cxy.website.dao.sys.*;
import com.cxy.website.model.sys.SysMenu;
import com.cxy.website.model.sys.SysRole;
import com.cxy.website.model.sys.SysUser;
import com.cxy.website.model.sys.UserRole;
import com.cxy.website.service.sys.LoginService;
import com.cxy.website.service.sys.SysUserService;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *@Description 用户管理service 实现类
 *@Author 侯森林
 *@Date 2019-9-18
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysRoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    SysMenuMapper menuMapper;

    @Autowired
    SysUserService userService;
    @Autowired
    LoginService loginService;

    /**
     *@Description 根据用户名查询用户信息
     *@Param userName
     *@Return UserSectionsMapper
     *@Author 侯森林
     *@Date 2019-9-9
     */
    @Override
    public SysUser findByUserName(String userName) {
        return userMapper.findUserByUserName(userName);
    }


    /**
     *@Description 分页条件查询用户信息
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public List<SysUser> findUser(String userName, Integer sex, Integer age, String beginDate, String endDate, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return userMapper.findUser(userName,sex, age,beginDate,endDate);
    }

    /**
     *@Description 返回分页条件查询用户数量信息
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public Map<String, Integer> countUserList(String userName, Integer sex, Integer age, String beginDate, String endDate) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        Integer totalRows = userMapper.countUserList(userName,sex, age,beginDate,endDate);
        map.put("totalRows",totalRows);
        return map;
    }

    /**
     *@Description 根据用户id查询用户信息
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public SysUser selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     *@Description 新增用户
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void insertSelective(SysUser user) {
     userMapper.insertSelective(user);
    }

    /**
     *@Description 修改用户信息
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void updateUser(SysUser user) {
     userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     *@Description 修改密码
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void changePassword(SysUser user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     *@Description 删除用户
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void deleteUserById(Integer userId) {
        SysUser sysUser=new SysUser();
        sysUser.setId(userId);
        sysUser.setIsDel(1);
        userMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     *@Description 获取角色列表
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public List<SysRole> findRoleList(Integer userId) {
       List<Integer> roleIdList= userRoleMapper.getRoleIdList(userId);
       List<SysRole> allRoleList=roleMapper.findAllRole();
       if(roleIdList.size()==0){
           return allRoleList;
       }else {
           for(Integer roleId:roleIdList){
               for(SysRole role:allRoleList){
                   if(roleId.equals(role.getId())){
                       role.setUserId(userId);
                       continue;
                   }
               }
               continue;
           }
           return allRoleList;
       }
    }

    /**
     *@Description 根据用户id删除用户所有角色
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void deleteAllUserRoleByUserId(Integer userId) {
        userRoleMapper.deleteUserRole(userId);
    }
    /**
     *@Description 用户分配角色
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void insertUserRole(Integer userId, List<Integer> list)
    {
        if(list.size()==0){
            return;
        }else {
            List<UserRole> userRoleList=new ArrayList<>();
            for(Integer i:list){
                UserRole userRole=new UserRole();
                userRole.setRoleId(i);
                userRole.setUserId(userId);
                userRoleList.add(userRole);
            }
            userRoleMapper.insertUserRole(userRoleList);
        }

    }

    /**
     *@Description 获取用户菜单
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public List<SysMenu> findMenuByUserId(Integer userId) {
        List<Integer> roleIdList= userRoleMapper.getRoleIdList(userId);
        List<Integer>menuIds=new ArrayList<>();
        if(roleIdList.size()>0){
            for(Integer id:roleIdList){
                List<Integer> menuIdList= sysRoleMenuMapper.selectMenuIdList(id);
                if(menuIdList.size()>0){
                    for(Integer mId:menuIdList){
                        menuIds.add(mId);
                    }
                }
            }
            List<Integer> menuIdLists = menuIds.stream().distinct().collect(Collectors.toList());
            List<SysMenu> menuList=menuMapper.findUserMenuList(menuIdLists);
            return menuList;
        }
        return null;
    }


	@Override
	public void updatePwd(SysUser user) {
		// TODO Auto-generated method stub
		 String userName = loginService.getCurrentUserName();
		SysUser adminUser = userService.findByUserName(userName);
		
		List<Integer> roleIdList = userRoleMapper.getRoleIdList(adminUser.getId());
		if (roleIdList.size() > 0) {
			for (int i = 0; i < roleIdList.size(); i++) {
				 System.out.println(roleIdList.get(i)+"ppppppp");
               if (roleIdList.contains(1)) {
            	  
				  userMapper.updateByPrimaryKey(user);
				  System.out.println("修改成功");
				 
			   }else {
				System.out.println("没有权限");
			}
			}
		}

	}



}
