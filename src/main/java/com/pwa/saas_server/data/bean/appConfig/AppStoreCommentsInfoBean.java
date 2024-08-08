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
public class AppStoreCommentsInfoBean {

    private String avatar; //评论者头像
    private String name; //评论者名字
    private String score; //评分，1-5的范围
    private String content; //评论内容



    //- APP评论区（列表，可以存储）
//            - 评论者头像
//    - 评论者名称
//    - 评分（1-5）
//            - 评论内容
}
