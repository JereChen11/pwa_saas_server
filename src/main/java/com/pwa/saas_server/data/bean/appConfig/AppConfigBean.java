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
public class AppConfigBean {
    private Long appConfigId; //app配置表主键id
    private AppBasicConfigBean appBasicConfigBean;
    private String appLanguage; //语言，支持多选，多元化配置
    private AppStoreConfigBean appStoreConfigBean;

}
