package com.example.demo.vo;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @PACKAGE_NAME: com.example.demo.vo
 * @NAME: UserInfo
 * @AUTHOR: Kevin Wen
 * @EMAIL: Kevin_Wen@sz.ctil.com
 * @DATE: 29/10/2024
 * @Description
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String id = IdUtil.getSnowflakeNextIdStr();

    private String username;

    private String password;

    private String token;

    private String refreshToken;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
