package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @PACKAGE_NAME: com.example.demo.vo
 * @NAME: HttpPayload
 * @AUTHOR: Kevin Wen
 * @EMAIL: Kevin_Wen@sz.ctil.com
 * @DATE: 29/10/2024
 * @Description
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpPayload<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msg;

    private Integer status = 0;

    private T data;

    public HttpPayload(T data) {
        this.data = data;
        this.msg = "ok";
    }
}
