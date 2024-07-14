package com.pwa.saas_server.mapper;

import com.pwa.saas_server.data.bean.InstallStatsBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jere
 */
@Mapper
public interface InstallStatsMapper {

    List<InstallStatsBean> selectAllInstallStats();

    List<InstallStatsBean> selectInstallStatsByIp(String userInstIp);

    List<InstallStatsBean> selectInstallStatsByUa(String userAgent);

    //TODO 晚点实现
//    List<InstallStatsBean> selectInstallStatsByCreateTime(String startTime, String endTime);

    InstallStatsBean selectInstallStatsById(Long instId);

    void insertInstallStats(InstallStatsBean installStatsBean);

    void deleteInstallStatsById(Long instId);

}
