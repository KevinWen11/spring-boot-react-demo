package com.example.demo.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import com.example.demo.UserContextThreadLocalHolder;
import com.example.demo.service.IAuthenticationService;
import com.example.demo.util.JwtTokenUtil;
import com.example.demo.vo.UserInfo;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.example.demo.constant.AuthConstant.*;

/**
 * @PACKAGE_NAME: com.example.demo.filter
 * @NAME: ApiAuthenticationFilter
 * @AUTHOR: Kevin Wen
 * @EMAIL: Kevin_Wen@sz.ctil.com
 * @DATE: 29/10/2024
 * @Description
**/
@WebFilter(urlPatterns = "/*",filterName = "apiAuthenticationFilter")
@Order(1)
@Slf4j
@Setter
public class ApiAuthenticationFilter implements Filter {

    @Value("${demo.security.excludePaths}")
    private String excludePaths;

    public List<String> getExcludePathList() {
        return StrUtil.split(excludePaths, ",");
    }

    @Resource
    private IAuthenticationService authenticationService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 过滤路径
        String requestURI = httpServletRequest.getRequestURI();

        String method = httpServletRequest.getMethod();
        if (HttpMethod.OPTIONS.matches(method)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (CollUtil.isNotEmpty(getExcludePathList())) {
            for (String excludePath : getExcludePathList()) {
                if (requestURI.startsWith(excludePath)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }

        String token = httpServletRequest.getHeader(TOKEN_HEADER);
        if (StrUtil.isBlank(token)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        try {
            JwtTokenUtil.validateToken(token);
            String username = JwtTokenUtil.getPayload(token, TOKEN_PAYLOAD_KEY);
            UserInfo byUsername = authenticationService.findByUsername(username);
            if (Objects.isNull(byUsername)){
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            //参数 chain 为代表当前 Filter 链的对象。
            UserContextThreadLocalHolder.setCurrentUser(byUsername);
            filterChain.doFilter(servletRequest,httpServletResponse);
        } catch (ValidateException e) {
            log.error("token is invalid",e);
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
