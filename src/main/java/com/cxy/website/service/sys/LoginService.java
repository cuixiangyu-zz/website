package com.cxy.website.service.sys;

import com.cxy.website.model.sys.LoginResult;
import org.apache.shiro.session.Session;

/**
 *@Description 用户登录service
 *@Author 侯森林
 *@Date 2019-9-18
 */

public interface LoginService {
    /**
     *@Description 用户认证
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    LoginResult login(String userName, String password);

    /**
     *@Description 获取当前用户名
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    String getCurrentUserName();
    /**
     *@Description 获取当前session
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    Session getSession();
    /**
     *@Description 用户退出系统
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    void logout();
}
