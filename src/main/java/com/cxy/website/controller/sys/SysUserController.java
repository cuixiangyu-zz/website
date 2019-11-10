package com.cxy.website.controller.sys;

import com.cxy.website.common.util.EncryptUtils;
import com.cxy.website.common.util.web.JsonData;
import com.cxy.website.model.PageDataResult;
import com.cxy.website.model.sys.SysRole;
import com.cxy.website.model.sys.SysUser;
import com.cxy.website.service.sys.LoginService;
import com.cxy.website.service.sys.RoleService;
import com.cxy.website.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 *@Description 用户管理控制器
 *@Author 侯森林
 *@Date 2019-9-18
 */
@Controller
@Api("用户管理")
@RequestMapping("/admin")
public class SysUserController {
    private String algorithmName = "md5";
    private int hashIterations = 2;
    private String salt = "8d78869f470951332959580424d4bf4f";

    @Autowired
    SysUserService userService;
    @Autowired
    LoginService loginService;
    @Autowired
    RoleService roleService;

    /**
     *@Description 分页条件查询用户
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequiresPermissions("nurse")
    @RequestMapping(value="/getUserList")
    //@RequiresPermissions("user:view")//权限管理;
    @ResponseBody
    @ApiOperation(value = "getUserList", notes = "获取用户列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sex", value = "性别",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "age", value = "年龄",paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "userName", value = "用户名",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数",paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "页数",paramType = "query", dataType = "Integer")
    })
    public Object getUserList(Integer sex, Integer age, String userName, String startTime , String endTime, Integer pageNum,
                                      Integer pageSize)
    {
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            List<SysUser> userList = userService.findUser(userName, sex, age, startTime, endTime,pageNum,pageSize);
            Map<String,Integer> map = userService.countUserList(userName, sex, age, startTime, endTime);
            pdr.setTotals(map.get("totalRows"));
            pdr.setList(userList);
            return  JsonData.buildSuccess(pdr);
        }catch (Exception e) {
            e.printStackTrace();
            return  JsonData.buildError();
        }
    }

    /**
     *@Description 保存用户信息
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping(value = "/userAdd",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "userAdd", notes = "保存用户信息(只需要后八条数据)", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sex", value = "性别(0：男生 1：女生）",paramType = "query",required = true,  dataType = "Integer",defaultValue ="0"),
            @ApiImplicitParam(name = "age", value = "年龄(数字）",paramType = "query",required = true,  dataType = "Integer",defaultValue = "20"),
            @ApiImplicitParam(name = "userName", value ="用户名",paramType = "query",required = true,  dataType = "String"),
            @ApiImplicitParam(name = "password", value ="用户密码(新增用户必填)",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "name", value = "姓名",paramType = "query",required = true,  dataType = "String",defaultValue = "张三"),
            @ApiImplicitParam(name = "tel", value = "电话",paramType = "query",required = true,  dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱",paramType = "query",required = true,  dataType = "String"),
            @ApiImplicitParam(name = "id", value = "用户id（修改时必填）",paramType = "query",dataType = "Integer")
    })
    public Object save(SysUser user){
       if(!checkRoleExists(user.getUserName(),user.getId())) {
           return JsonData.buildError("用户已存在",1);
       }
        user.setSalt(this.salt);
        if(user.getId()==null) {
            try {
                byte b=0;
                user.setState(b);
                user.setCreateTime(new Date());
                user.setIsDel(0);
                String encryptPwd = EncryptUtils.encrypt(user.getPassword(),user.getCredentialsSalt(),this.algorithmName,this.hashIterations);
                user.setPassword(encryptPwd);
                userService.insertSelective(user);
               return JsonData.buildSuccess(null,"新增用户成功",1);
            } catch (Exception e) {
                e.printStackTrace();
                return JsonData.buildError("新增用户失败",1);
            }
        }
        else {
            try {
                userService.updateUser(user);
                return JsonData.buildSuccess(null,"修改用户信息成功",1);
            } catch (Exception e) {
                e.printStackTrace();
                return JsonData.buildError("修改用户信息失败",1);
            }
        }
    }

    /**
     *@Description 判断用户是否存在
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    public boolean checkRoleExists(String userName,Integer userId) {
        SysUser user = userService.findByUserName(userName);
        if(userId==null){
            if (user == null) {
               return true;
            }else {
                return false;
            }
        }else {
            if (user == null) {
                return true;
            }else if(userId.equals(user.getId())){
                return true;
            }else {
                return false;
            }
        }

    }


    /**
     *@Description 删除用户
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "deleteUser", notes = "根据用户id删除用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",paramType = "query",required = true, dataType = "Integer")
    })
    public Object userDel(Integer id){
        try {
            userService.deleteUserById(id);
            return JsonData.buildSuccess(null,"删除用户成功",1);
        }catch (Exception e)
        {
            e.printStackTrace();
            return JsonData.buildError("删除用户失败",1);


        }

    }

    /**
     *@Description 获取所有角色
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping(value = "/toGetUserRole",method= RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "toGetUserRole", notes = "获取角色列表 userId为空则该id的用户不具有当前角色", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",paramType = "query",required = true,  dataType = "Integer")
    })
    public Object getUserRole(Integer id)
    {
        if (id==null) {
            return null;
        }
        List<SysRole> list = userService.findRoleList(id);
        List<Map<String,Object>> roleList=new ArrayList<>();
        for(SysRole role:list){
            Map roleMap=new HashMap();
            roleMap.put("id",role.getId());
            roleMap.put("label",role.getRoleName());
            if(role.getUserId()==null){
                roleMap.put("select",false);
            }else {
                roleMap.put("select",true);
            }
            roleList.add(roleMap);
        }
        return JsonData.buildSuccess(roleList);
    }

    /**
     *@Description 用户分配角色
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping(value="/toGrantUserRole")
    @ResponseBody
    @ApiOperation(value = "toGrantUserRole", notes = "用户分配角色", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",paramType = "query",required = true,  dataType = "Integer"),
            @ApiImplicitParam(name = "roleIdList", value = "角色id数组",paramType = "query",required = true,allowMultiple=true,dataType = "Integer")
    })
    public Object grantUserRole(Integer id, Integer[]roleIdList)
    {
        try {
            userService.deleteAllUserRoleByUserId(id);
            userService.insertUserRole(id,Arrays.asList(roleIdList));
            return JsonData.buildSuccess(null,"用户分配角色成功",1);
        }catch (Exception e)
        {
            e.printStackTrace();
            return JsonData.buildError("用户分配角色失败",1);
        }
    }

    /**
     *@Description 修改密码
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @RequestMapping(value="/changePassword",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "changePassword", notes = "修改密码", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "原密码",paramType = "query",required = true,  dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码",paramType = "query",required = true,  dataType = "String")
    })
    public Object changePassword(String password,String newPassword)
    {
        Map<String,String> map = new HashMap<>();
        if(newPassword==null)
        {
            return JsonData.buildError("密码不能为空",1);
        }

        String userName = loginService.getCurrentUserName();
        if(userName==null || userName.isEmpty())
        {

            return JsonData.buildError("用户错误",1);
        }
        SysUser user = userService.findByUserName(userName);
        if(user==null)
        {

            return JsonData.buildError("用户错误",1);
        }

        String encryptPwd = EncryptUtils.encrypt(password,user.getCredentialsSalt(),this.algorithmName,this.hashIterations);
        if(!encryptPwd.equals(user.getPassword()))
        {
            return JsonData.buildError("当前用户密码不正确",1);
        }

        String encryptNewPwd = EncryptUtils.encrypt(newPassword,user.getCredentialsSalt(),this.algorithmName,this.hashIterations);
        user.setPassword(encryptNewPwd);
        userService.changePassword(user);

        return JsonData.buildSuccess(null,"密码重置成功",1);
    }


    /**
     * 管理员修改用户密码
     * 
     * @return
     */
    @RequestMapping(value="/updatePwd" ,method = RequestMethod.POST)
    @ResponseBody
    public JsonData updatePwd(String password,String newPassword,Integer userId) {
    	 SysUser user = userService.selectByPrimaryKey(userId);
    	 if(user==null)
         {

             return JsonData.buildError("用户错误",1);
         }

         String encryptPwd = EncryptUtils.encrypt(password,user.getCredentialsSalt(),this.algorithmName,this.hashIterations);
         if(!encryptPwd.equals(user.getPassword()))
         {
             return JsonData.buildError("当前用户密码不正确",1);
         }

         String encryptNewPwd = EncryptUtils.encrypt(newPassword,user.getCredentialsSalt(),this.algorithmName,this.hashIterations);
         user.setPassword(encryptNewPwd);
         userService.updatePwd(user);
         return JsonData.buildSuccess(null,"成功",0);
				
	}


}
