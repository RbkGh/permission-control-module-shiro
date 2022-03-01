package com.rodneyboachie.permcontrol.security;

import com.rodneyboachie.permcontrol.db.UserEntity;
import com.rodneyboachie.permcontrol.mapper.UsersMapper;
import com.rodneyboachie.permcontrol.util.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * author: acerbk
 * Date: 24/02/2022
 * Time: 3:28 am
 */
@Service
@Slf4j
@AllArgsConstructor
public class MyRealm extends JdbcRealm {

    private UsersMapper userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * This method is called only when user permissions need to be detected, such as checkRole, checkPermission, etc.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        UserEntity user = userService.getUserRoleAndPermission(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getUserRole());
        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission()));
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * By default, this method is used to verify whether the user name is correct or not, and an error can be thrown.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // Decrypt to get the username for comparison with the database version
        String username = JWTUtil.getUsername(token);
        if (username == null)
            throw new AuthenticationException("token invalid");

        UserEntity userBean = userService.getUserRoleAndPermission(username);
        if (userBean == null)
            throw new AuthenticationException("User doesn't exist!");

        if (!JWTUtil.verify(token, username, userBean.getPassword()))
            throw new AuthenticationException("Username or password error");

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
