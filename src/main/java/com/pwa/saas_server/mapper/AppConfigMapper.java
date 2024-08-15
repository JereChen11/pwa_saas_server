package com.pwa.saas_server.mapper;

import com.pwa.saas_server.data.bean.appConfig.AppBasicConfigBean;
import com.pwa.saas_server.data.bean.appConfig.AppConfigBean;
import com.pwa.saas_server.data.bean.appConfig.AppStoreCommentsInfoBean;
import com.pwa.saas_server.data.bean.appConfig.AppStoreInfoBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jere
 */
@Mapper
public interface AppConfigMapper {
    List<AppBasicConfigBean> selectAllAppBasicConfigList();

    AppBasicConfigBean selectAppBasicConfigByAppId(int appId);

    List<AppStoreInfoBean> getAllAppStoreInfo();

    List<AppStoreInfoBean> selectAppStoreInfoByAppId(int appId);

    List<AppStoreCommentsInfoBean> getAllAppStoreComments();

    List<AppStoreCommentsInfoBean> selectAppStoreCommentsByAppStoreId(int appStoreId);

    void insertAppBasicConfig(AppBasicConfigBean appBasicConfigBean);

    void updateAppBasicConfig(AppBasicConfigBean appBasicConfigBean);

    void insertAppStoreInfo(AppStoreInfoBean appStoreInfoBean);

    void updateAppStoreInfo(AppStoreInfoBean appStoreInfoBean);

    void insertAppStoreCommentsInfo(AppStoreCommentsInfoBean appStoreCommentsInfoBean);

    void updateAppStoreCommentsInfo(AppStoreCommentsInfoBean appStoreCommentsInfoBean);
}
