package com.pwa.saas_server.security;


import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.pwa.saas_server.utils.MyConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author jere
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final static String AUTH_HEADER = "Authorization";
    private final static String AUTH_HEADER_TYPE = "Bearer";

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AuthenticationException {
        // get token from header:  Authorization: Bearer <token>
        String authHeader = request.getHeader(AUTH_HEADER);
        logger.error("authHeader = " + authHeader);
        if (Objects.isNull(authHeader) || !authHeader.startsWith(AUTH_HEADER_TYPE)) {
            filterChain.doFilter(request, response);
            return;
        }

        //取出Token
        String authToken = authHeader.split(" ")[1];
        log.info("authToken:{}", authToken);
        JWT jwt = JWT.of(authToken);
        Object exp = jwt.getPayload("exp");
        logger.error("current time = " + System.currentTimeMillis());
        //默认Token永不过期
        boolean isExpired = false;
        if (exp != null) {
            isExpired = System.currentTimeMillis() > Long.parseLong(exp.toString()); //true表示token过期了
        }
        if (isExpired) {
            logger.error("过期啦。。。。。。。");
        }

        //验证token
        if (!JWTUtil.verify(authToken, MyConstant.JWT_SIGN_KEY.getBytes(StandardCharsets.UTF_8)) || isExpired) {
            log.info("invalid token");
            if (isExpired) {
                //token已经过期，需要重新登入。
                response.setStatus(403);
            }
            filterChain.doFilter(request, response);
            return;
        }

        //验证token通过，开始解析token
        final String username = (String) JWTUtil.parseToken(authToken).getPayload(MyConstant.JWT_PAYLOAD_KEY_USERNAME);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }


}