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
    /*
//    todo 暂时先只支持单语言
    private List<String> appLanguage; //语言，支持多选，多元本地化配置
    private List<AppStoreConfigBean> appStoreConfigBean;
     */
    private String appLanguage; //语言，支持多选，多元本地化配置
    private AppStoreInfoBean appStoreInfoBean;

    private AppDisplaySettingBean appDisplaySettingBean;
}
