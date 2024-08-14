package com.pwa.saas_server.service;

import com.pwa.saas_server.data.bean.appConfig.AppBasicConfigBean;
import com.pwa.saas_server.data.bean.appConfig.AppConfigBean;
import com.pwa.saas_server.data.bean.appConfig.AppStoreCommentsInfoBean;
import com.pwa.saas_server.data.bean.appConfig.AppStoreInfoBean;

import java.util.List;

/**
 * @author jere
 */
public interface AppConfigService {

    List<AppBasicConfigBean> getAllAppBasicConfigList();

    AppBasicConfigBean getAppBasicConfigByAppId(int appId);

    List<AppStoreInfoBean> getAppStoreInfoByAppId(int appId);

    List<AppStoreCommentsInfoBean> getAppStoreCommentsByAppStoreId(int appStoreId);


}
