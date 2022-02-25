package com.rodneyboachie.permcontrol.service;

import com.rodneyboachie.permcontrol.db.UserEntity;
import com.rodneyboachie.permcontrol.exceptions.UnauthorizedException;
import com.rodneyboachie.permcontrol.mapper.UserMapper;
import com.rodneyboachie.permcontrol.security.BCryptPasswordService;
import com.rodneyboachie.permcontrol.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 9:54 pm
 */
@Service
@AllArgsConstructor
public class AuthService {

    private UserMapper userMapper;

    private BCryptPasswordService bCryptPasswordService;

    public String getAuthorizedToken(String username, String password) throws
            UnauthorizedException {

        UserEntity userEntity = userMapper.getUserByUsername(username);
        if (userEntity == null)
            throw new UnauthorizedException("User Unauthorized");

        if (!bCryptPasswordService.passwordsMatch(password, userEntity.getPassword()))
            throw new UnauthorizedException("User Unauthorized - wrong password");

        String jwtAccessToken = JWTUtil.sign(username, userEntity.getPassword());

        return jwtAccessToken;
    }
}
