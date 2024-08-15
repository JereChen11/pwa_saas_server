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
    private int commentId;
    private String commenterAvatar; //评论者头像
    private String commenterName; //评论者名字
    private int rating; //评分，1-5的范围
    private String commentText; //评论内容
    private int appStoreId;
}
