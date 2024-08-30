package com.pwa.saas_server.service;

import com.pwa.saas_server.utils.JwtTokenUtil;
import com.pwa.saas_server.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * JWT token 黑名单处理服务
 *
 * @author jere
 */
@Service
public class TokenBlackListService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static final String BLACKLIST_PREFIX = "token_blacklist_";

    public void addTokenToBlacklist(String token, long expirationTimeInSeconds) {
        redisUtil.set(BLACKLIST_PREFIX + token, "true", expirationTimeInSeconds);
    }

    public void addTokenToBlacklist(String token) {
        // 获取 JWT token 的过期时间
        long expirationTimeInSeconds = jwtTokenUtil.getExpTimeFromToken(token);
        logger.error("addTokenToBlacklist expirationTimeInSeconds = " + expirationTimeInSeconds);
        redisUtil.set(BLACKLIST_PREFIX + token, "true", expirationTimeInSeconds);
    }

    public boolean isTokenBlacklisted(String token) {
        return redisUtil.hasKey(BLACKLIST_PREFIX + token);
    }

}
