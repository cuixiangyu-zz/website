package com.cxy.website.model.sys;

/**
 *@Description 登录结果实体类
 *@Author 侯森林
 *@Date 2019-9-18
 */
public class LoginResult {
    private boolean isLogin = false;//是否登录
    private String result;//登录结果
    private String token;//身份令牌
    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
