package com.example.demo.api;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import com.example.demo.UserContextThreadLocalHolder;
import com.example.demo.service.IAuthenticationService;
import com.example.demo.vo.HttpPayload;
import com.example.demo.vo.UserInfo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.example.demo.constant.AuthConstant.*;

/**
 * @PACKAGE_NAME: com.example.demo.api
 * @NAME: LoginController
 * @AUTHOR: Kevin Wen
 * @EMAIL: Kevin_Wen@sz.ctil.com
 * @DATE: 29/10/2024
 * @Description
 **/
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private static final UserInfo internalUser = new UserInfo("admin","123456");

    private static final UserInfo externalUser = new UserInfo("adminExt","123456");

    private static final List<UserInfo> users = Collections.unmodifiableList(CollUtil.newArrayList(internalUser,externalUser));

    private IAuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseBody
    public UserInfo login(@RequestBody UserInfo userInfo, HttpServletResponse response){
        if (Objects.isNull(userInfo)){
            return null;
        }
        UserInfo authUser = authenticationService.login(userInfo.getUsername(),userInfo.getPassword());
        if (Objects.nonNull(authUser)){
            response.setHeader(TOKEN_HEADER, authUser.getToken());
            response.setHeader(REFRESH_TOKEN_HEADER, authUser.getRefreshToken());
        }
        return authUser;
    }


    @GetMapping("/logout")
    public HttpPayload<Void> logout(HttpServletResponse response){
        response.setHeader(TOKEN_HEADER, "");
        response.setHeader(REFRESH_TOKEN_HEADER, "");
        return new HttpPayload<>(null);
    }


    @GetMapping("/test")
    public HttpPayload<Void> test(HttpServletResponse response){
        return new HttpPayload<>(null);
    }


}
