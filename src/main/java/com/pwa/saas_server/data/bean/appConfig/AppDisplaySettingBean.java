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
public class AppDisplaySettingBean {
    private String themeColor; //主题颜色，例如：#E16868
    private String backgroundColor; //背景颜色，例如：#38E122
}
