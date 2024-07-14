package com.pwa.saas_server.controller;

import com.pwa.saas_server.data.base.Result;
import com.pwa.saas_server.data.bean.InstallStatsBean;
import com.pwa.saas_server.service.InstallStatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
