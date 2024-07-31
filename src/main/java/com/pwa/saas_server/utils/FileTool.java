package com.pwa.saas_server.utils;

import java.util.UUID;

/**
 * 文件相关的工具类
 *
 * @author jere
 */
public class FileTool {


    /**
     * 随机生成UUID来作为文件名
     * @return 文件名
     */
    public static String generateFileName() {
        return UUID.randomUUID().toString();
    }

}
