package com.cxy.website.common.config.shiro;

import com.cxy.website.common.constants.LoginType;
import org.apache.shiro.authc.UsernamePasswordToken;

public class DoctorUserToken extends UsernamePasswordToken {


    private LoginType type;


    public DoctorUserToken() {
        super();
    }


    public DoctorUserToken(String username, String password, LoginType type, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
        this.type = type;
    }
    /**免密登录*/
    public DoctorUserToken(String username) {
        super(username, "", false, null);
        this.type = LoginType.NOPASSWD;
    }
    /**账号密码登录*/
    public DoctorUserToken(String username, String password) {
        super(username, "", false, null);
        this.type = LoginType.PASSWORD;
    }

    public LoginType getType() {
        return type;
    }


    public void setType(LoginType type) {
        this.type = type;
    }


}
