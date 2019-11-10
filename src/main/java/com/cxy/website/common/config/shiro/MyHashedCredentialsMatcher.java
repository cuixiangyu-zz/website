package com.cxy.website.common.config.shiro;

import com.cxy.website.common.constants.LoginType;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {


    /**
     * 免密登录验证
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (token instanceof DoctorUserToken) {
            DoctorUserToken tk = (DoctorUserToken) token;
            if (tk.getType().equals(LoginType.NOPASSWD)) {
                return true;
            }
        }
        Object tokenHashedCredentials = hashProvidedCredentials(token, info);
        Object accountCredentials = getCredentials(info);
        return equals(tokenHashedCredentials, accountCredentials);

    }


}
