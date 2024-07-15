package com.pwa.saas_server.serviceImpl;

import com.pwa.saas_server.data.bean.InstallStatsBean;
import com.pwa.saas_server.mapper.InstallStatsMapper;
import com.pwa.saas_server.service.InstallStatsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author jere
 */
@Service
public class InstallStatsServiceImpl implements InstallStatsService {

    @Autowired
    private InstallStatsMapper installStatsMapper;

    @Override
    public List<InstallStatsBean> getAllInstallStats() {
        return installStatsMapper.selectAllInstallStats();
    }

    @Override
    public List<InstallStatsBean> getInstallStatsByIp(String userInstIp) {
        return installStatsMapper.selectInstallStatsByIp(userInstIp);
    }

    @Override
    public List<InstallStatsBean> getInstallStatsByUa(String userAgent) {
        return installStatsMapper.selectInstallStatsByUa(userAgent);
    }

    @Override
    public List<InstallStatsBean> getInstallStatsByStartTime(Timestamp startTime) {
        return installStatsMapper.selectInstallStatsByStartTime(startTime);
    }

    @Override
    public List<InstallStatsBean> getInstallStatsByTimeRange(@Param("startTime") Timestamp startTime,
                                                             @Param("endTime") Timestamp endTime) {
        return installStatsMapper.selectInstallStatsByTimeRange(startTime, endTime);
    }

    @Override
    public InstallStatsBean getInstallStatsById(Long instId) {
        return installStatsMapper.selectInstallStatsById(instId);
    }

    @Override
    public void insertInstallStats(InstallStatsBean installStatsBean) {
        installStatsMapper.insertInstallStats(installStatsBean);
    }

    @Override
    public void deleteInstallStatsById(Long instId) {
        installStatsMapper.deleteInstallStatsById(instId);
    }
}
