package com.example.demo.service;

import com.example.demo.vo.UserInfo;

/**
 * @PACKAGE_NAME: com.example.demo.service
 * @NAME: IAuthenticationService
 * @AUTHOR: Kevin Wen
 * @EMAIL: Kevin_Wen@sz.ctil.com
 * @DATE: 29/10/2024
 * @Description
 **/
public interface IAuthenticationService {


    UserInfo login(String username, String password);


    UserInfo findByUsername(String username);

}
