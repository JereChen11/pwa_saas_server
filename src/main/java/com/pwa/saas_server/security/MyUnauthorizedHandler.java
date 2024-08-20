package com.pwa.saas_server.security;

import com.google.gson.Gson;
import com.pwa.saas_server.data.base.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 未认证时回调，也就是说没有登录
 *
 * @author jere
 */
@Slf4j
@Component
public class MyUnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized error", authException);
        System.out.println("authorized 异常捕获 --> " + authException.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //自定义返回 Result<String>，方便客户端统一接口使用
        Result<String> unauthorizedResult = Result.error("认证失败，请先登入！");
        response.getWriter().write(new Gson().toJson(unauthorizedResult));
        response.getWriter().flush();
    }
}
