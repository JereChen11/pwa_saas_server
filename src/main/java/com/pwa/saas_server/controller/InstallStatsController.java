package com.pwa.saas_server.controller;

import com.pwa.saas_server.data.base.Result;
import com.pwa.saas_server.data.base.ResultCode;
import com.pwa.saas_server.data.bean.InstallStatsBean;
import com.pwa.saas_server.service.InstallStatsService;
import com.pwa.saas_server.utils.IpTool;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author jere
 */
@RestController
@RequestMapping("/api/install")
public class InstallStatsController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private InstallStatsService installStatsService;

    @GetMapping("/getAllInstallStats")
    public Result<List<InstallStatsBean>> getAllInstallStats() {
        return Result.success(installStatsService.getAllInstallStats());
    }

    @GetMapping("/getInstallStatsByIp")
    public Result<List<InstallStatsBean>> getInstallStatsByIp(String userInstIp) {
        return Result.success(installStatsService.getInstallStatsByIp(userInstIp));
    }

    @GetMapping("/getInstallStatsByUa")
    public Result<List<InstallStatsBean>> getInstallStatsByUa(String userAgent) {
        return Result.success(installStatsService.getInstallStatsByUa(userAgent));
    }

    @GetMapping("/getInstallStatsById")
    public Result<InstallStatsBean> getInstallStatsById(Long instId) {
        return Result.success(installStatsService.getInstallStatsById(instId));
    }

    @GetMapping("/getInstallStatsByStartTime")
    public Result<List<InstallStatsBean>> getInstallStatsByStartTime(Timestamp startTime) {
        logger.error("getInstallStatsByStartTime startTime = " + startTime);
        return Result.success(installStatsService.getInstallStatsByStartTime(startTime));
    }

    @GetMapping("/getInstallStatsByTimeRange")
    public Result<List<InstallStatsBean>> getInstallStatsByTimeRange(Timestamp startTime, Timestamp endTime) {
        return Result.success(installStatsService.getInstallStatsByTimeRange(startTime, endTime));
    }

    @PostMapping("/stats")
    public Result<String> insertInstallStats(HttpServletRequest request, InstallStatsBean installStatsBean) {
        logger.info("insertInstallStats " + installStatsBean.toString());
        String clientIp = IpTool.getInstance().getClientIpAddress(request);
        logger.error("clientIp = " + clientIp);
        installStatsBean.setUserInstIp(clientIp);
        if (StringUtils.isEmpty(installStatsBean.getUserAgent())
                || StringUtils.isEmpty(installStatsBean.getUserLanguage())
                || StringUtils.isEmpty(installStatsBean.getUserNetType())
        ) {
            return Result.error(ResultCode.PARAM_ERROR);
        }

        installStatsService.insertInstallStats(installStatsBean);
        return Result.success("successful!");
    }

    @PostMapping("/deleteInstallStatsById")
    public Result<String> deleteInstallStatsById(@RequestParam("instId") Long instId) {
        installStatsService.deleteInstallStatsById(instId);
        return Result.success("deleteInstallStatsById instId = " + instId + " successful!");
    }

}
