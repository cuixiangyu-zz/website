package com.cxy.website.service.sys.impl;


import com.cxy.website.common.config.shiro.CommonUserToken;
import com.cxy.website.model.sys.LoginResult;
import com.cxy.website.service.sys.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 *@Description 用户登录service接口实现类
 *@Author 侯森林
 *@Date 2019-9-18
 */

@Service
public class LoginServiceImpl implements LoginService {

    /**
     *@Description 用户认证
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public LoginResult login(String userName, String password) {
        LoginResult loginResult = new LoginResult();
        if(userName==null || userName.isEmpty())
        {
            loginResult.setLogin(false);
            loginResult.setResult("用户名为空");
            return loginResult;
        }
        String msg="";
        // 1、获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();

        // 2、判断当前用户是否登录
        if (currentUser.isAuthenticated() == false) {

        }

        // 3、将用户名和密码封装到UsernamePasswordToken
//        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        UsernamePasswordToken token = new CommonUserToken(userName, password);
        // 4、认证
        try {
            currentUser.login(token);// 传到MyAuthorizingRealm类中的方法进行认证
            Session session = currentUser.getSession();
            session.setAttribute("userName", userName);
            loginResult.setLogin(true);
            loginResult.setToken(String.valueOf(session.getId()));
            return loginResult;
            //return "/index";
        }catch (UnknownAccountException e)
        {
            e.printStackTrace();
            msg = "账号不存在：";
        }
        catch (IncorrectCredentialsException e)
        {
            msg = "密码不正确：";
        }
        catch (AuthenticationException e) {
            e.printStackTrace();
            msg="用户验证失败";
        }

        loginResult.setLogin(false);
        loginResult.setResult(msg);

        return loginResult;
    }
    /**
     *@Description 用户退出系统
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    /**
     *@Description 获取当前用户名
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public String getCurrentUserName() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return (String)session.getAttribute("userName");
    }
    /**
     *@Description 获取当前session
     *@Param
     *@Return
     *@Author 侯森林
     *@Date 2019-9-18
     */
    @Override
    public Session getSession() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return session;
    }
}
