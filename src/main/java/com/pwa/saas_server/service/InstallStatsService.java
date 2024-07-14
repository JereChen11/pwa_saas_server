package com.pwa.saas_server.service;

import com.pwa.saas_server.data.bean.InstallStatsBean;

import java.util.List;

/**
 * @author jere
 */
public interface InstallStatsService {
    List<InstallStatsBean> getAllInstallStats();

    List<InstallStatsBean> getInstallStatsByIp(String userInstIp);

    List<InstallStatsBean> getInstallStatsByUa(String userAgent);

    //TODO 晚点实现
//    List<InstallStatsBean> getInstallStatsByCreateTime(String startTime, String endTime);

    InstallStatsBean getInstallStatsById(Long instId);

    void insertInstallStats(InstallStatsBean installStatsBean);

    void deleteInstallStatsById(Long instId);

}
