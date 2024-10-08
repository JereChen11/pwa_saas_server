package com.pwa.saas_server.data.bean.appConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jere
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppBasicConfigBean {
    private int appId;
    private String appName;
    private String appNote;
    private String appLogo;
}
