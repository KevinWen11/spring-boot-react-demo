package com.example.demo.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.AsymmetricJWTSigner;
import cn.hutool.jwt.signers.HMacJWTSigner;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;

import java.util.Optional;

import static com.example.demo.constant.AuthConstant.TOKEN_PAYLOAD_KEY;

/**
 * @PACKAGE_NAME: com.example.demo.util
 * @NAME: JwtTokenUtil
 * @AUTHOR: Kevin Wen
 * @EMAIL: Kevin_Wen@sz.ctil.com
 * @DATE: 29/10/2024
 * @Description
 **/
public class JwtTokenUtil {

    private static final JWTSigner signer = JWTSignerUtil.hs256("AZzr42".getBytes());


    public static String getToken(String username) {
        DateTime now = DateUtil.date();
        return JWT.create().setSigner(signer).setPayload(TOKEN_PAYLOAD_KEY, username).setNotBefore(now).setIssuedAt(now).setExpiresAt(now.offsetNew(DateField.DAY_OF_MONTH, 1)).sign();
    }


    public static String getRefreshToken(String username) {
        DateTime now = DateUtil.date();
        return JWT.create().setSigner(signer).setPayload(TOKEN_PAYLOAD_KEY, username).setNotBefore(now).setIssuedAt(now).setExpiresAt(now.offsetNew(DateField.DAY_OF_MONTH, 7)).sign();
    }


    public static void validateToken(String token) {
        JWTValidator.of(token).validateAlgorithm(signer).validateDate();
    }


    public static String getPayload(String token,String key){
       return Optional.ofNullable(JWT.of(token).setSigner(signer).getPayload(key)).map(Object::toString).orElse(null);
    }

}
