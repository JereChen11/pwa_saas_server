package com.pwa.saas_server.serviceImpl;

import com.pwa.saas_server.data.bean.appConfig.AppBasicConfigBean;
import com.pwa.saas_server.data.bean.appConfig.AppStoreCommentsInfoBean;
import com.pwa.saas_server.data.bean.appConfig.AppStoreInfoBean;
import com.pwa.saas_server.mapper.AppConfigMapper;
import com.pwa.saas_server.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jere
 */
@Service
public class AppConfigServiceImpl implements AppConfigService {

    @Autowired
    private AppConfigMapper appConfigMapper;


    @Override
    public List<AppBasicConfigBean> getAllAppBasicConfigList() {
        return appConfigMapper.selectAllAppBasicConfigList();
    }

    @Override
    public AppBasicConfigBean getAppBasicConfigByAppId(int appId) {
        return appConfigMapper.selectAppBasicConfigByAppId(appId);
    }

    @Override
    public List<AppStoreInfoBean> getAllAppStoreInfo() {
        return appConfigMapper.getAllAppStoreInfo();
    }

    @Override
    public List<AppStoreInfoBean> getAppStoreInfoByAppId(int appId) {
        return appConfigMapper.selectAppStoreInfoByAppId(appId);
    }

    @Override
    public List<AppStoreCommentsInfoBean> getAllAppStoreComments() {
        return appConfigMapper.getAllAppStoreComments();
    }

    @Override
    public List<AppStoreCommentsInfoBean> getAppStoreCommentsByAppStoreId(int appStoreId) {
        return appConfigMapper.selectAppStoreCommentsByAppStoreId(appStoreId);
    }

    @Override
    public void insertAppBasicConfig(AppBasicConfigBean appBasicConfigBean) {
        appConfigMapper.insertAppBasicConfig(appBasicConfigBean);
    }

    @Override
    public void updateAppBasicConfig(AppBasicConfigBean appBasicConfigBean) {
        appConfigMapper.updateAppBasicConfig(appBasicConfigBean);
    }

    @Override
    public void insertAppStoreInfo(AppStoreInfoBean appStoreInfoBean) {
        appConfigMapper.insertAppStoreInfo(appStoreInfoBean);
    }

    @Override
    public void updateAppStoreInfo(AppStoreInfoBean appStoreInfoBean) {
        appConfigMapper.updateAppStoreInfo(appStoreInfoBean);
    }

    @Override
    public void insertAppStoreCommentsInfo(AppStoreCommentsInfoBean appStoreCommentsInfoBean) {
        appConfigMapper.insertAppStoreCommentsInfo(appStoreCommentsInfoBean);
    }

    @Override
    public void updateAppStoreCommentsInfo(AppStoreCommentsInfoBean appStoreCommentsInfoBean) {
        appConfigMapper.updateAppStoreCommentsInfo(appStoreCommentsInfoBean);
    }
}
