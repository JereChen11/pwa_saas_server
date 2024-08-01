package com.pwa.saas_server.data.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author jere
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SingleFileUploadBean {
    private Long fileId; //文件表ID，主键
    private String origin; //来源
    private String originFileName; //原始文件名
    private String finalFileName; // 最终随机生成的文件名
    private String filePath; // 文件存储的路径
    private String imageType; //图片类型
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdTime; //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedTime; //更新时间

    public SingleFileUploadBean(String origin, String originFileName, String finalFileName,
                                String filePath, String imageType) {
        this.origin = origin;
        this.originFileName = originFileName;
        this.finalFileName = finalFileName;
        this.filePath = filePath;
        this.imageType = imageType;
    }
}
