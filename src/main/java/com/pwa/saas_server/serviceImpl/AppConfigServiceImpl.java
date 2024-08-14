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
    public List<AppStoreInfoBean> getAppStoreInfoByAppId(int appId) {
        return appConfigMapper.selectAppStoreInfoByAppId(appId);
    }

    @Override
    public List<AppStoreCommentsInfoBean> getAppStoreCommentsByAppStoreId(int appStoreId) {
        return appConfigMapper.selectAppStoreCommentsByAppStoreId(appStoreId);
    }
}
