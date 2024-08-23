package com.pwa.saas_server.data.base;


/**
 * @author jere
 */
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 失败
     */
    ERROR(400, "error"),

    /**
     * 参数错误
     */
    PARAM_ERROR(400, "PARAM_ERROR"),

    /**
     * token已过期，请重新登入
     */
    INVALID_TOKEN(403, "INVALID TOKEN，Please login again."),

    /**
     * 未知的接口
     */
    UNKNOWN_INTERFACE(404, "UNKNOWN_INTERFACE"),

    /**
     * 请求方式不支持
     */
    REQ_MODE_NOT_SUPPORTED(405, "REQ_MODE_NOT_SUPPORTED"),

    /**
     * 网络超时
     */
    NETWORK_TIMEOUT(408, "NETWORK_TIMEOUT"),

    /**
     * 文件超出最大限制
     */
    FILE_OUT_MAX(413, "FILE_OUT_MAX"),

    /**
     * 文件格式不正确
     */
    FILE_FORMAT_ERROR(415, "FILE_FORMAT_ERROR"),

    /**
     * Json解析异常
     */
    JSON_FORMAT_ERROR(511, "JSON_FORMAT_ERROR"),

    /**
     * Sql解析异常
     */
    SQL_ERROR(512, "SQL_ERROR"),

    /**
     * 系统异常
     */
    SYS_ERROR(500, "SYS_ERROR");

    private int code;

    private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
