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
public class AppStoreExhibitInfoBean {
    private String downloadCount; //APP下载数
    private int commentsNumber; //APP评论数
    private String introduction; //APP简介
    private List<String> detailedPictureList; //APP详情图（3-5张）
    private List<String> tagList; //APP标签
    private String score; //五个等级范围的评分数据，例如：10，0，0，0，1。
}
