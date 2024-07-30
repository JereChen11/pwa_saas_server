package com.pwa.saas_server.data.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

/**
 * @author jere
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstallStatsBean {

    private Long instId; //安装表ID，主键
    private String origin; //来源
    private String userInstIp; //用户安装IP
    private String userAgent; //用户ua
    private String userBrowserType; //用户浏览器类型，例如：chrome，safari，opera..
    private String userBrowserVersion; //用户浏览器版本号，例如：chrome 116
    private String userLanguage; //用户浏览器语言
    private String userNetType; //用户的网络连接类型（如 WiFi 4G）
    private Boolean isInstSuccess; //是否安装成功
    private String customParams; //根据业务需求的自定义参数
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdTime; //创建时间

}
