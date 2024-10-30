package com.example.demo.api;

import com.example.demo.UserContextThreadLocalHolder;
import com.example.demo.vo.HttpPayload;
import com.example.demo.vo.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @PACKAGE_NAME: com.example.demo.api
 * @NAME: AppController
 * @AUTHOR: Kevin Wen
 * @EMAIL: Kevin_Wen@sz.ctil.com
 * @DATE: 29/10/2024
 * @Description
 **/
@RestController
@RequestMapping("/api/app")
@AllArgsConstructor
public class AppController {


    @GetMapping("/data")
    public HttpPayload<HashMap<String,String>> getAppData() {
        return new HttpPayload<>(new HashMap<>());
    }

}
