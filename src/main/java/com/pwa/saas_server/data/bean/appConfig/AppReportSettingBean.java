package com.pwa.saas_server.data.bean.appConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jere
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppReportSettingBean {
    //todo 待实现。需要考虑后期事件上报的具体业务。
    private int reportAttr; //归因上报属性。1：归因到最早一次广告点击；2：归因到最近一次广告点击。
    private int reportWindowPeriod; //上报窗口期（1-720)小时
}
