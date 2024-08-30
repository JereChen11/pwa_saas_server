package com.pwa.saas_server.utils;

import cn.hutool.jwt.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author jere
 */
@Component
public class JwtTokenUtil {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 这个方法用于从 JWT token 中解析出过期时间
     * @param token token
     * @return 过期时间，单位为秒
     */
    public long getExpTimeFromToken(String token) {
        // 解析 JWT token 获取过期时间
        JWT jwt = JWT.of(token);
        //注意！这里提取出来的时间为秒，而System.currentTimeMillis()获取的本地时间的单位为毫秒。
        Object exp = jwt.getPayload("exp");
        logger.error("current time = " + System.currentTimeMillis());
        long expiredTime = 0L;
        if (exp != null) {
            expiredTime = Long.parseLong(exp + "000") - System.currentTimeMillis();
        }
        logger.error("getExpTimeFromToken expiredTime = " + expiredTime);

        return expiredTime / 1000;
    }
}
