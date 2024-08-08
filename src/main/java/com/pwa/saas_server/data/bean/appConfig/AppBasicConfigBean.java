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
    private String appName;
    private String appRemark; //app备注
    private String appLogo;
}
