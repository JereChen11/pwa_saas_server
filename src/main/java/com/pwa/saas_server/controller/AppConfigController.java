package com.pwa.saas_server.controller;

import com.pwa.saas_server.data.base.Result;
import com.pwa.saas_server.data.bean.appConfig.AppBasicConfigBean;
import com.pwa.saas_server.data.bean.appConfig.AppConfigBean;
import com.pwa.saas_server.data.bean.appConfig.AppStoreCommentsInfoBean;
import com.pwa.saas_server.data.bean.appConfig.AppStoreInfoBean;
import com.pwa.saas_server.service.AppConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jere
 */
@RestController
@RequestMapping("/api/appConfig")
public class AppConfigController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AppConfigService appConfigService;


    @GetMapping("/getAllAppBasicConfigList")
    public Result<List<AppBasicConfigBean>> getAllAppBasicConfigList() {
        return Result.success(appConfigService.getAllAppBasicConfigList());
    }

    @GetMapping("/getAppBasicConfigByAppId")
    public Result<AppBasicConfigBean> getAppBasicConfigByAppId(@RequestParam("appId") int appId) {
        return Result.success(appConfigService.getAppBasicConfigByAppId(appId));
    }

    @GetMapping("/getAppStoreInfoByAppId")
    public Result<List<AppStoreInfoBean>> getAppStoreInfoByAppId(@RequestParam("appId") int appId) {
        return Result.success(appConfigService.getAppStoreInfoByAppId(appId));
    }

    @GetMapping("/getAppStoreCommentsByAppStoreId")
    public Result<List<AppStoreCommentsInfoBean>> getAppStoreCommentsByAppStoreId(@RequestParam("appStoreId") int appStoreId) {
        return Result.success(appConfigService.getAppStoreCommentsByAppStoreId(appStoreId));
    }

}
