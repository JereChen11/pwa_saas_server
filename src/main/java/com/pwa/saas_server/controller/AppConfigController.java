package com.pwa.saas_server.controller;

import com.pwa.saas_server.data.base.Result;
import com.pwa.saas_server.data.base.ResultCode;
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

    @GetMapping("/getAllAppStoreInfo")
    public Result<List<AppStoreInfoBean>> getAllAppStoreInfo() {
        return Result.success(appConfigService.getAllAppStoreInfo());
    }

    @GetMapping("/getAppStoreInfoByAppId")
    public Result<List<AppStoreInfoBean>> getAppStoreInfoByAppId(@RequestParam("appId") int appId) {
        return Result.success(appConfigService.getAppStoreInfoByAppId(appId));
    }

    @GetMapping("/getAllAppStoreComments")
    public Result<List<AppStoreCommentsInfoBean>> getAllAppStoreComments() {
        return Result.success(appConfigService.getAllAppStoreComments());
    }

    @GetMapping("/getAppStoreCommentsByAppStoreId")
    public Result<List<AppStoreCommentsInfoBean>> getAppStoreCommentsByAppStoreId(@RequestParam("appStoreId") int appStoreId) {
        return Result.success(appConfigService.getAppStoreCommentsByAppStoreId(appStoreId));
    }

    @GetMapping("/getAppConfigByAppId")
    public Result<AppConfigBean> getAppConfigByAppId(@RequestParam("appId") int appId) {

        AppBasicConfigBean appBasicConfigBean = appConfigService.getAppBasicConfigByAppId(appId);
        List<AppStoreInfoBean> appStoreInfoBeanList = appConfigService.getAppStoreInfoByAppId(appId);
        AppStoreInfoBean firstAppStoreInfoBean = appStoreInfoBeanList.get(0);
        if (firstAppStoreInfoBean == null) {
            return Result.error("appStoreInfoBeanList.get(0) == null");
        }
        List<AppStoreCommentsInfoBean> appStoreCommentsInfoBeanList = appConfigService.getAppStoreCommentsByAppStoreId(firstAppStoreInfoBean.getAppStoreId());
        firstAppStoreInfoBean.setCommentsInfoBeanList(appStoreCommentsInfoBeanList);
        AppConfigBean appConfigBean = new AppConfigBean(appBasicConfigBean, "en", firstAppStoreInfoBean);

        return Result.success(appConfigBean);
    }

    @PostMapping("/insertAppBasicConfig")
    public Result<String> insertAppBasicConfig(AppBasicConfigBean appBasicConfigBean) {
        appConfigService.insertAppBasicConfig(appBasicConfigBean);
        String insertAppBasicConfigResult = "insertAppBasicConfig: " + appBasicConfigBean.getAppName() + ", " + appBasicConfigBean.getAppLogo() + ", " + appBasicConfigBean.getAppNote();
        return Result.success(insertAppBasicConfigResult);
    }

    @PostMapping("/updateAppBasicConfig")
    public Result<String> updateAppBasicConfig(AppBasicConfigBean appBasicConfigBean) {
        logger.info("updateAppBasicConfig: " + appBasicConfigBean.toString());
        if (appBasicConfigBean.getAppId() == 0) {
            return Result.error(ResultCode.PARAM_ERROR);
        }
        appConfigService.updateAppBasicConfig(appBasicConfigBean);
        String updateAppBasicConfigResult = "updateAppBasicConfig: " + appBasicConfigBean.toString();
        return Result.success(updateAppBasicConfigResult);
    }

    @PostMapping("/insertAppStoreInfo")
    public Result<String> insertAppStoreInfo(AppStoreInfoBean appStoreInfoBean) {
        appConfigService.insertAppStoreInfo(appStoreInfoBean);
        String insertAppStoreInfoResult = "insertAppStoreInfo: " + appStoreInfoBean.toString();
        return Result.success(insertAppStoreInfoResult);
    }

    @PostMapping("/updateAppStoreInfo")
    public Result<String> updateAppStoreInfo(AppStoreInfoBean appStoreInfoBean) {
        logger.info("updateAppStoreInfo: " + appStoreInfoBean.toString());
        if (appStoreInfoBean.getAppStoreId() == 0) {
            return Result.error(ResultCode.PARAM_ERROR);
        }
        appConfigService.updateAppStoreInfo(appStoreInfoBean);

        String updateAppStoreInfoResult = "updateAppStoreInfo: " + appStoreInfoBean.toString();
        return Result.success(updateAppStoreInfoResult);
    }

    @PostMapping("/insertAppStoreCommentsInfo")
    public Result<String> insertAppStoreCommentsInfo(AppStoreCommentsInfoBean appStoreCommentsInfoBean) {
        appConfigService.insertAppStoreCommentsInfo(appStoreCommentsInfoBean);
        String insertAppStoreCommentsInfoResult = "insertAppStoreCommentsInfo: " + appStoreCommentsInfoBean.toString();
        return Result.success(insertAppStoreCommentsInfoResult);
    }

    @PostMapping("/updateAppStoreCommentsInfo")
    public Result<String> updateAppStoreCommentsInfo(AppStoreCommentsInfoBean appStoreCommentsInfoBean) {
        logger.info("updateAppStoreCommentsInfo: " + appStoreCommentsInfoBean.toString());
        if (appStoreCommentsInfoBean.getAppStoreId() == 0) {
            return Result.error(ResultCode.PARAM_ERROR);
        }
        appConfigService.updateAppStoreCommentsInfo(appStoreCommentsInfoBean);

        String updateAppStoreCommentsInfoResult = "updateAppStoreCommentsInfo: " + appStoreCommentsInfoBean.toString();
        return Result.success(updateAppStoreCommentsInfoResult);
    }

}
