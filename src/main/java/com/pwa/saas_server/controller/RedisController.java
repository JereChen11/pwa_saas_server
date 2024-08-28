package com.pwa.saas_server.controller;

import com.pwa.saas_server.data.base.Result;
import com.pwa.saas_server.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jere
 */
@RestController
@RequestMapping("/api/redis")
public class RedisController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/saveTest")
    public Result<String> save(String key, String value) {
        logger.info("save, key = " + key + ", value = " + value);
        redisUtil.set(key, value);
        return Result.success("save key = " + key + ", value = " + value + " successful");
    }

    @GetMapping("/getTest")
    public Result<String> get(String key) {
        Object valueObj = redisUtil.get(key);
        if (valueObj == null) {
            return Result.error("get the value by key = " + key + " failed!");
        }


        logger.info("get, key = " + key + ", value = " + valueObj.toString());

        return Result.success("get key = " + key + ", value = " + valueObj + " successful");
    }

}
