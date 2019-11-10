package com.cxy.website.common.config.shiro;

import com.cxy.website.model.sys.SysMenu;
import com.cxy.website.model.sys.SysRole;
import com.cxy.website.model.sys.SysUser;
import com.cxy.website.service.sys.RoleService;
import com.cxy.website.service.sys.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    SysUserService userService;


    @Autowired
    RoleService roleService;

    /**
     *@Description 授权
     *@Param
     *@Return
     *@Author 侯森林
     *@Date
     *@Time
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限判断-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果授权部分没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();

        for ( SysRole role : user.getSysUserRoleList()) {
            authorizationInfo.addRole(role.getRoleName());
        }

        for (SysMenu menu : user.getSysUserMenuList()) {
            authorizationInfo.addStringPermission(menu.getPermission());
        }
        return authorizationInfo;
    }

    @Override
    /**
    *@Description 身份认证
    *@Param
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken instanceof CommonUserToken) { //普通用户的认证逻辑
            log.info("普通用户身份认证-->MyShiroRealm.doGetAuthenticationInfo()");
            //获取用户的输入的账号.
            String userName = (String) authenticationToken.getPrincipal();
//        System.out.println(token.getCredentials());
            //通过username从数据库中查找 User对象，如果找到，没找到.
            //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
            SysUser user = userService.findByUserName(userName);
            System.out.println("-->>user=" + user);
            if (user == null) {
                throw new UnknownAccountException();
            }
            user.setSysUserMenuList(userService.findMenuByUserId(user.getId()));
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user, //这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                    user.getPassword(), //密码
                    ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                    getName()  //realm name
            );
            return authenticationInfo;
        }else {
            return null;
        }

    }
}
