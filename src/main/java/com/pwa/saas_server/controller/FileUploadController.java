package com.pwa.saas_server.controller;

import com.pwa.saas_server.data.base.Result;
import com.pwa.saas_server.data.base.ResultCode;
import com.pwa.saas_server.utils.FileTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author jere
 */
@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error(ResultCode.PARAM_ERROR);
        }
        logger.error("uploadDir = " + uploadDir);
        logger.error("file getContentType = " + file.getContentType());

        if (file.getContentType() == null
                || !Objects.requireNonNull(file.getContentType()).startsWith("image")) {
            return Result.error(ResultCode.FILE_FORMAT_ERROR);
        }

        logger.error("file size = " + file.getSize());
        //限制文件大小为5M
        final long fileLimitSize = 5 * 1024 * 1024;
        if (file.getSize() > fileLimitSize) {
            return Result.error(ResultCode.FILE_OUT_MAX);
        }

        try {
            // 获取文件名并构建文件路径
            String originFileName = file.getOriginalFilename();
            //这里不能直接用文件名来，而是用UUID来定义文件名字，然后这个UUID要存入数据库，方便后期搜索。
            if (originFileName == null) {
                return Result.error(ResultCode.FILE_FORMAT_ERROR);
            }

            String[] sd = originFileName.split("\\.");
            String imageType = sd[sd.length - 1];
            StringBuilder newPathSb = new StringBuilder()
                    .append(uploadDir)
                    .append(File.separator)
                    .append(FileTool.generateFileName())
                    .append(".")
                    .append(imageType);

            //todo 怎么避免重复上传呢？
            Path path = Paths.get(newPathSb.toString());

            logger.error("absolute path = " + path.toAbsolutePath());

            // 创建目标文件夹（如果不存在）
            Files.createDirectories(path.getParent());

            // 保存文件
            Files.write(path, file.getBytes());

            return Result.success("File uploaded successfully: " + originFileName);

        } catch (IOException e) {
            return Result.error("Failed to upload file: " + e.getMessage());
        }
    }
}
