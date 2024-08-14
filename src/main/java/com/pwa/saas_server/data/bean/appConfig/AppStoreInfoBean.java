package com.pwa.saas_server.data.bean.appConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jere
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppStoreInfoBean {
    private int appStoreId;
    private String appName;
    private String appLogo;
//    private AppStoreExhibitInfoBean appStoreExhibitInfoBean;
    private String companyName; //公司名称
    private int downloadCount; //APP下载数
    private int reviewCount; //APP评论数
    private String appDescription; //APP简介
    private String appDetailImages; //APP详情图（3-5张），例如：'["/images/detail1.png", "/images/detail2.png", "/images/detail3.png"]'
    private String appTags; //APP标签
    private String ratingSettings; //五个等级范围的评分数据，例如：10，0，0，0，1。
    private int appId;

//    private List<AppStoreCommentsInfoBean> commentsInfoBeanList;
}
