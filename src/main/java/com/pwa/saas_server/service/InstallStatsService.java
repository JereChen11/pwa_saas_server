package com.pwa.saas_server.service;

import com.pwa.saas_server.data.bean.InstallStatsBean;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author jere
 */
public interface InstallStatsService {
    List<InstallStatsBean> getAllInstallStats();

    List<InstallStatsBean> getInstallStatsByIp(String userInstIp);

    List<InstallStatsBean> getInstallStatsByUa(String userAgent);

    List<InstallStatsBean> getInstallStatsByStartTime(Timestamp startTime);

    List<InstallStatsBean> getInstallStatsByTimeRange(Timestamp startTime, Timestamp endTime);

    InstallStatsBean getInstallStatsById(Long instId);

    void insertInstallStats(InstallStatsBean installStatsBean);

    void deleteInstallStatsById(Long instId);

}
