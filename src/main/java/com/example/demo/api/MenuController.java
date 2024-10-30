package com.example.demo.api;

import com.example.demo.vo.HttpPayload;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @PACKAGE_NAME: com.example.demo.api
 * @NAME: MenuController
 * @AUTHOR: Kevin Wen
 * @EMAIL: Kevin_Wen@sz.ctil.com
 * @DATE: 29/10/2024
 * @Description
 **/
@RestController
@RequestMapping("/api/menu")
@AllArgsConstructor
public class MenuController {


    @GetMapping("/fetch")
    public HttpPayload<HashMap<String,String>> getAppData() {
        return new HttpPayload<>(new HashMap<>());
    }


}
