package com.pwa.saas_server.security;


import cn.hutool.jwt.JWTUtil;
import com.pwa.saas_server.service.TokenBlackListService;
import com.pwa.saas_server.utils.JwtTokenUtil;
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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TokenBlackListService tokenBlackListService;

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
        //默认Token永不过期
        boolean isExpired = false;
        long expirationTimeInSeconds = jwtTokenUtil.getExpTimeFromToken(authToken);
        if (expirationTimeInSeconds <= 0) {
            isExpired = true;
        }

        //如果Token已存在于token黑名单中的话，说明用户已经登出了。
        boolean isClientLogout = tokenBlackListService.isTokenBlacklisted(authToken);
        logger.error("doFilterInternal isExpired = " + isExpired + ", isClientLogout = " + isClientLogout);
        //验证token
        if (!JWTUtil.verify(authToken, MyConstant.JWT_SIGN_KEY.getBytes(StandardCharsets.UTF_8)) || isExpired || isClientLogout) {
            log.info("invalid token");
            if (isExpired || isClientLogout) {
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