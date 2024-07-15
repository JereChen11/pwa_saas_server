package com.pwa.saas_server.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * IP 检测工具
 *
 * @author jere
 */
public class IpTool {

    private IpTool() {
    }

    private static class IpToolHolder {
        private static final IpTool INSTANCE = new IpTool();
    }

    public static IpTool getInstance() {
        return IpToolHolder.INSTANCE;
    }

    /**
     * 获取客户端IP地址
     *
     * @param request 请求信息
     * @return IP地址
     */
    public String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
