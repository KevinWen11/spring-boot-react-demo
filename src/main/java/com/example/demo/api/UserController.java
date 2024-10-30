package com.example.demo.api;

import com.example.demo.UserContextThreadLocalHolder;
import com.example.demo.service.IAuthenticationService;
import com.example.demo.vo.HttpPayload;
import com.example.demo.vo.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PACKAGE_NAME: com.example.demo.api
 * @NAME: UserController
 * @AUTHOR: Kevin Wen
 * @EMAIL: Kevin_Wen@sz.ctil.com
 * @DATE: 29/10/2024
 * @Description
 **/
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {


    private IAuthenticationService authenticationService;


    @GetMapping("/info")
    public HttpPayload<UserInfo> getUserInfo() {
        return new HttpPayload<>(UserContextThreadLocalHolder.getCurrentUser());
    }

}
