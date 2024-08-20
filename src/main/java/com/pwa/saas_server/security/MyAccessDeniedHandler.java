package com.pwa.saas_server.security;

import com.google.gson.Gson;
import com.pwa.saas_server.data.base.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 当访问接口没有权限时回调
 *
 * @author jere
 */

@Slf4j
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("access error", accessDeniedException);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //自定义返回 Result<String>，方便客户端统一接口使用
        Result<String> accessDeniedResult = Result.error("禁止访问");
        response.getWriter().write(new Gson().toJson(accessDeniedResult));
        response.getWriter().flush();
    }
}
