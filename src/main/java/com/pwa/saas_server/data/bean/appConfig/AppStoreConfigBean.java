package com.pwa.saas_server.data.bean.appConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jere
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppStoreConfigBean {
    private AppBasicConfigBean appBasicConfigBean;
    private AppStoreExhibitInfoBean appStoreExhibitInfoBean;
    private List<AppStoreCommentsInfoBean> commentsInfoBeanList;
}
