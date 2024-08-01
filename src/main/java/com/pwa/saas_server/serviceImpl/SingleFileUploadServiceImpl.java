package com.pwa.saas_server.serviceImpl;

import com.pwa.saas_server.data.bean.SingleFileUploadBean;
import com.pwa.saas_server.mapper.SingleFileUploadMapper;
import com.pwa.saas_server.service.SingleFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jere
 */
@Service
public class SingleFileUploadServiceImpl implements SingleFileUploadService {

    @Autowired
    private SingleFileUploadMapper singleFileUploadMapper;

    @Override
    public List<SingleFileUploadBean> getAllSingleFiles() {
        return singleFileUploadMapper.selectAllSingleFiles();
    }

    @Override
    public List<SingleFileUploadBean> getSingleFilesByOrigin(String origin) {
        return singleFileUploadMapper.selectSingleFilesByOrigin(origin);
    }

    @Override
    public SingleFileUploadBean getSingleFilesByFileId(Long fileId) {
        return singleFileUploadMapper.selectSingleFilesByFileId(fileId);
    }

    @Override
    public void insertSingleFile(SingleFileUploadBean singleFileUploadBean) {
        singleFileUploadMapper.insertSingleFile(singleFileUploadBean);
    }

    @Override
    public void deleteSingleFileByFileId(Long fileId) {
        singleFileUploadMapper.deleteSingleFileByFileId(fileId);
    }
}
