package com.cxy.website.controller.sys;

import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.PageDataResult;
import com.cxy.website.model.sys.SysMenu;
import com.cxy.website.model.sys.SysRole;
import com.cxy.website.service.sys.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
*@Description 角色管理控制器
*@Author 侯森林
*@Date 2019-9-18
*/
@Controller
@Api("角色管理")
@RequestMapping("/admin")
public class SysRoleController {
    @Autowired
    RoleService tbRoleService;
    /**
    *@Description 分页查询角色信息
    *@Param roleName 角色名称 pageSize 条数 pageNo 页数
    *@Return PageDataResult
    *@Author 侯森林
    *@Date 2019-9-18
    */
    @RequestMapping(value="/getRoleList")
    @ResponseBody
    @ApiOperation(value = "getRoleList", notes = "获取角色列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称（admin）",paramType = "query",  dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "页数",paramType = "query",dataType = "Integer")
    })
    public Object getRole(String roleName, Integer pageSize, Integer pageNum)
    {
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            List<SysRole> machineAdminList = tbRoleService.findRole(roleName,pageNum,pageSize);
            Map<String,Integer> map = tbRoleService.countRoleList(roleName);
            pdr.setTotals(map.get("totalRows"));
            pdr.setList(machineAdminList);
            return  JsonData.buildSuccess(pdr);
        }catch (Exception e) {
            e.printStackTrace();
            return  JsonData.buildError();
        }
    }

    /**
     *@Description 保存角色信息
     *@Param Role 角色信息
     *@Return 角色是否保存成功提示信息
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping(value="/saveRoleMessage",method =RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "saveRoleMessage", notes = "保存角色信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称",paramType = "query",required = true,  dataType = "String",defaultValue = "随机字符串"),
            @ApiImplicitParam(name = "description", value = "描述",paramType = "query",required = true,  dataType = "String",defaultValue = "aaa"),
            @ApiImplicitParam(name = "remark", value = "备注",paramType = "query",required = true,  dataType = "String",defaultValue = ""),
            @ApiImplicitParam(name = "id", value = "角色id（修改角色信息）",paramType = "query",dataType = "Integer")

    })
    public Object saveRoleMessage(SysRole role)
    {
        boolean tag=checkRoleExists(role.getRoleName(),role.getId());
        if(!tag){
            return JsonData.buildError("角色已存在",1);
        }
        if(role.getId()==null) {
            try {
                role.setCreateTime(new Date());
                role.setIsDel(0);
                tbRoleService.insertRole(role);
              return JsonData.buildSuccess(null,"新增角色成功",1);
            }catch (Exception e){
               return JsonData.buildError("新增角色失败",1);
            }
        }else {
            try {
                tbRoleService.updateRoleById(role);
                return JsonData.buildSuccess(null,"修改角色成功",1);
            }catch (Exception e){
                return JsonData.buildError("修改角色失败",1);
            }

        }
    }

    /**
     *@Description 判断角色是否存在
     *@Param role 角色名称 roleId 角色id
     *@Return 角色是否存在提示信息
     *@Author 侯森林
     *@Date 2019-9-18
     */
//    @RequestMapping("/checkRoleExists")
//    @ResponseBody
//    @ApiOperation(value = "checkRoleExists", notes = "判断角色是否存在", httpMethod = "GET")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "roleName", value = "角色名称",paramType = "query",required = true,  dataType = "String"),
//            @ApiImplicitParam(name = "roleId", value = "角色id（修改角色信息时）",paramType = "query",dataType = "Integer")
//    })
    public boolean checkRoleExists(String roleName,Integer roleId)
    {
        SysRole role=tbRoleService.findByRoleName(roleName);
        if(roleId==null){
            if(role==null){
                return true;
            }else {
                return false;
            }
        }else {
                if(role==null){
                    return true;
                }else if(roleId.equals(role.getId())) {
                    return true;
                }else {
                    return false;
                }

        }
    }

    /**
     *@Description 根据角色id删除角色
     *@Param roleId 角色id
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping(value = "/roleDelete")
    @ResponseBody
    @ApiOperation(value = "roleDelete", notes = "根据角色id删除角色", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id",paramType = "query",required = true,dataType ="Integer")
    })
    public Object delete(Integer id) {


        try {
            tbRoleService.deleteRole(id);
            return JsonData.buildSuccess(null,"删除角色成功",0);
        }catch (Exception e){
           return JsonData.buildError("删除角色失败",1);
        }
    }
    /**
     *@Description 获取所有菜单，角色已拥有的菜单select:true
     *@Param roleId 角色id
     *@Return Object 角色权限
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping(value = "/getRoleMenu" ,method= RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "getRoleMenu", notes = "根据角色id获取菜单（返回数据角色id不为空的话证明该角色拥有该权限）", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id",paramType = "query",required = true,dataType = "Integer",defaultValue = "2")
    })
    public Object getRolePermission(Integer id)
    {
        if (id==null) {
            return null;
        }
        List<SysMenu> list = tbRoleService.getMenuList(id);
        List<Map>childList=new ArrayList<>();
        for(SysMenu menu:list){
            if(menu.getPid()==1){
                Map<String,Object> chidMap=new HashMap();
                chidMap.put("id",menu.getId());
                chidMap.put("label",menu.getName());
                if(menu.getRoleId()==null){
                    chidMap.put("select",false);
                }else {
                    chidMap.put("select",true);
                }
                List<Map>grandSonList=new ArrayList<>();
                for(SysMenu grandsonMenu:list){
                    if(grandsonMenu.getPid()==(int)menu.getId()) {
                        Map<String,Object> grandSon = new HashMap();
                        grandSon.put("id", grandsonMenu.getId());
                        grandSon.put("label", grandsonMenu.getName());
                        if(grandsonMenu.getRoleId()==null){
                            grandSon.put("select",false);
                        }else {
                            grandSon.put("select",true);
                        }
                        grandSonList.add(grandSon);
                    }
                }
                chidMap.put("children",grandSonList);
                childList.add(chidMap);
            }

        }
        return JsonData.buildSuccess(childList);


    }
    /**
     *@Description 角色授权
     *@Param roleId 角色id  permissionIdList 权限id
     *@Return 角色授权是否成功
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping(value = "/toAuthorize",method =RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "toAuthorize", notes = "给角色分配权限", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id",paramType = "query",required = true,  dataType = "Integer",defaultValue ="2"),
            @ApiImplicitParam(name = "menuIdList", value = "菜单id组成的数组",paramType = "query",required = true,allowMultiple=true,dataType = "Integer")
    })
    public Object toAuthorize(Integer id,Integer[]menuIdList) {
        try {
            List<Integer>idList=Arrays.asList(menuIdList);
            tbRoleService.deleteRoleMenu(id);
            tbRoleService.insertRoleMenu(id,idList);
            return JsonData.buildSuccess(null,"角色授权成功",1);
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("角色授权失败",1);
        }

    }
}
