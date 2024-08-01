package com.pwa.saas_server.mapper;

import com.pwa.saas_server.data.bean.SingleFileUploadBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jere
 */
@Mapper
public interface SingleFileUploadMapper {

    List<SingleFileUploadBean> selectAllSingleFiles();

    List<SingleFileUploadBean> selectSingleFilesByOrigin(String origin);

    SingleFileUploadBean selectSingleFilesByFileId(Long fileId);

    void insertSingleFile(SingleFileUploadBean singleFileUploadBean);

    void deleteSingleFileByFileId(Long fileId);
}
