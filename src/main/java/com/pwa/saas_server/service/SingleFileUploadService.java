package com.pwa.saas_server.service;

import com.pwa.saas_server.data.bean.SingleFileUploadBean;

import java.util.List;

/**
 * @author jere
 */
public interface SingleFileUploadService {
    List<SingleFileUploadBean> getAllSingleFiles();

    List<SingleFileUploadBean> getSingleFilesByOrigin(String origin);

    SingleFileUploadBean getSingleFilesByFileId(Long fileId);

    void insertSingleFile(SingleFileUploadBean singleFileUploadBean);

    void deleteSingleFileByFileId(Long fileId);
}
