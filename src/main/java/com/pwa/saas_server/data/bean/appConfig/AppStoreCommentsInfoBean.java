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
//    comment_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID，自动递增',
//    commenter_avatar VARCHAR(255) COMMENT '评论者头像',
//    commenter_name VARCHAR(255) COMMENT '评论者名称',
//    rating TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5) COMMENT '评分（1-5）',
//    comment_text TEXT COMMENT '评论内容',
//    app_store_id INT COMMENT '商店信息ID，tb_app_store_info表的主键',
    private int commentId;
    private String commenterAvatar; //评论者头像
    private String commenterName; //评论者名字
    private int rating; //评分，1-5的范围
    private String commentText; //评论内容
    private int appStoreId;
}
