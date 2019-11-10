package com.cxy.website.controller.sys;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.sys.SysMenu;
import com.cxy.website.model.sys.SysUser;
import com.cxy.website.service.sys.LoginService;
import com.cxy.website.service.sys.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Description 菜单权限控制器
 *@Author 侯森林
 *@Date 2019-9-18
 */
@Controller
@RequestMapping("/admin")
public class SysMenuController {
    @Autowired
    LoginService loginService;
    @Autowired
    SysUserService userService;

    /**
     *@Description 根据用户权限获取路由菜单
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-10-17
     */
    @RequestMapping(value = "/getMenu",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getMenu", notes = "获取菜单路由", httpMethod = "GET")
    public Object getMenuTree(){
        SysUser user=userService.findByUserName(loginService.getCurrentUserName());
        List<SysMenu> menuList=userService.findMenuByUserId(user.getId());
        List<Map<String,Object>> menuMapList=new ArrayList<>();
        if(menuList!=null && menuList.size()>0){
            for(SysMenu menu:menuList){
                if(menu.getPid().equals(1)){
                    Map menuMap=new HashMap();
                    menuMap.put("title",menu.getName());
                    menuMap.put("component",menu.getMenuUrl());
                    menuMap.put("name",getConvert(menu.getPermission()));
                    Map map=new HashMap();
                    map.put("title",menu.getName());
                    menuMap.put("meta",map);
                    menuMap.put("path",menu.getPermission());
                    List<Map<String,Object>> sonMenuMapList=new ArrayList<>();
                    for(SysMenu sonMenu:menuList){
                        if(sonMenu.getPid().equals(menu.getId())){
                            Map sonmMenuMap=new HashMap();
                            sonmMenuMap.put("title",sonMenu.getName());
                            sonmMenuMap.put("component",sonMenu.getMenuUrl());
                            sonmMenuMap.put("name",getConvert(sonMenu.getPermission()));
                            Map map1=new HashMap();
                            map1.put("title",sonMenu.getName());
                            sonmMenuMap.put("meta",map1);
                            sonmMenuMap.put("path",sonMenu.getPermission());
                            sonmMenuMap.put("children","");
                            sonMenuMapList.add(sonmMenuMap);
                        }
                    }
                    menuMap.put("children",sonMenuMapList);
                    menuMapList.add(menuMap);
                }
            }
        }
        else
        {
            throw new UnauthorizedException();
        }
        return  JsonData.buildSuccess(menuMapList);
    }
    /**
     *@Description 将字符串首字母变为大写
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-10-17
     */
    public static String getConvert(String str) {
        String first = str.substring(0, 1);
        String after = str.substring(1); //substring(1),获取索引位置1后面所有剩余的字符串
        first = first.toUpperCase();
        after = after.toLowerCase();
        return  first + after;
    }

}