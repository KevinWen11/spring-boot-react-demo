package com.example.demo.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import com.example.demo.util.JwtTokenUtil;
import com.example.demo.vo.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.example.demo.constant.AuthConstant.TOKEN_PAYLOAD_KEY;

/**
 * @PACKAGE_NAME: com.example.demo.service
 * @NAME: AuthenticationService
 * @AUTHOR: Kevin Wen
 * @EMAIL: Kevin_Wen@sz.ctil.com
 * @DATE: 29/10/2024
 * @Description
 **/
@Service
public class AuthenticationService implements IAuthenticationService {

    private static final UserInfo internalUser = new UserInfo("admin","123456");

    private static final UserInfo externalUser = new UserInfo("adminExt","123456");

    private static final List<UserInfo> users = Collections.unmodifiableList(CollUtil.newArrayList(internalUser,externalUser));

    @Override
    public UserInfo login(String username, String password) {
        UserInfo authUser = users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst().orElse(null);
        if (Objects.isNull(authUser)){
            return null;
        }
        String token = JwtTokenUtil.getToken(authUser.getUsername());
        String refreshToken = JwtTokenUtil.getRefreshToken(authUser.getUsername());
        authUser.setToken(token);
        authUser.setRefreshToken(refreshToken);
        return authUser;
    }

    @Override
    public UserInfo findByUsername(String username) {
        if (StrUtil.isBlank(username)) {
            return null;
        }
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }


}
