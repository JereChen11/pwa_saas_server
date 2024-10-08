package com.pwa.saas_server.controller;

import com.pwa.saas_server.data.base.Result;
import com.pwa.saas_server.data.base.ResultCode;
import com.pwa.saas_server.data.bean.SingleFileUploadBean;
import com.pwa.saas_server.service.SingleFileUploadService;
import com.pwa.saas_server.utils.FileTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * 单文件上传接口实现。
 *
 * @author jere
 */
@RestController
@RequestMapping("/api/file/single")
public class SingleFileUploadController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SingleFileUploadService singleFileUploadService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/getAllSingleFiles")
    public Result<List<SingleFileUploadBean>> getAllSingleFiles() {
        return Result.success(singleFileUploadService.getAllSingleFiles());
    }

    @GetMapping("/getSingleFilesByOrigin")
    public Result<List<SingleFileUploadBean>> getSingleFilesByOrigin(@RequestParam("origin") String origin) {
        return Result.success(singleFileUploadService.getSingleFilesByOrigin(origin));
    }

    @GetMapping("/getSingleFilesByFileId")
    public Result<SingleFileUploadBean> getSingleFilesByFileId(@RequestParam("fileId") Long fileId) {
        return Result.success(singleFileUploadService.getSingleFilesByFileId(fileId));
    }

    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam("origin") String origin, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error(ResultCode.PARAM_ERROR);
        }
        logger.error("uploadDir = " + uploadDir);
        logger.error("file getContentType = " + file.getContentType());

        if (file.getContentType() == null || !Objects.requireNonNull(file.getContentType()).startsWith("image")) {
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
            String finalFileName = FileTool.generateFileName();

            String[] fileNameArray = originFileName.split("\\.");
            String imageType = fileNameArray[fileNameArray.length - 1];
            StringBuilder newPathSb = new StringBuilder().append(uploadDir)
                    .append(File.separator)
                    .append(finalFileName)
                    .append(".")
                    .append(imageType);
            logger.error("newPathString = " + newPathSb);
            //todo 怎么避免重复上传呢？
            //根据原始名称去数据库中搜一下，看是否存在
            Path path = Paths.get(newPathSb.toString());
            logger.error("absolute path = " + path.toAbsolutePath().toString());

            //插入到数据库中
            singleFileUploadService.insertSingleFile(new SingleFileUploadBean(
                            origin, originFileName, finalFileName, path.toAbsolutePath().toString(), imageType
                    )
            );

            // 创建目标文件夹（如果不存在）
            Files.createDirectories(path.getParent());

            // 保存文件
            Files.write(path, file.getBytes());

            return Result.success("File uploaded successfully: " + originFileName);

        } catch (IOException e) {
            return Result.error("Failed to upload file: " + e.getMessage());
        }
    }

    @PostMapping("/uploadMoreTest")
    public Result<String> uploadFile(@RequestParam("origin") String origin,
                                     @RequestParam("file1") MultipartFile file1,
                                     @RequestParam("file2") MultipartFile file2) {
        if (file1.isEmpty() || file1.isEmpty()) {
            return Result.error(ResultCode.PARAM_ERROR);
        }

        if (file1.getContentType() == null || !Objects.requireNonNull(file1.getContentType()).startsWith("image")
                || file2.getContentType() == null || !Objects.requireNonNull(file2.getContentType()).startsWith("image")) {
            return Result.error(ResultCode.FILE_FORMAT_ERROR);
        }

        //限制文件大小为5M
        final long fileLimitSize = 5 * 1024 * 1024;
        if (file1.getSize() > fileLimitSize || file2.getSize() > fileLimitSize) {
            return Result.error(ResultCode.FILE_OUT_MAX);
        }

        try {
            Path path1 = getPathByFile(file1);
            Path path2 = getPathByFile(file2);

            if (path1 == null || path2 == null) {
                return Result.error(ResultCode.PARAM_ERROR);
            }

            // 创建目标文件夹（如果不存在）
            Files.createDirectories(path1.getParent());
            // 保存图片1
            Files.write(path1, file1.getBytes());
            // 保存图片2
            Files.write(path2, file2.getBytes());

            /* 插入到数据库中
            singleFileUploadService.insertSingleFile(new SingleFileUploadBean(
                            origin, originFileName, finalFileName, path.toAbsolutePath().toString(), imageType
                    )
            );*/

            return Result.success("uploaded " + path1 + " and " + path2 + " successfully!");
        } catch (IOException e) {
            return Result.error("Failed to upload file: " + e.getMessage());
        }
    }

    private Path getPathByFile(MultipartFile file) {
        // 获取文件名并构建文件路径
        String originFileName = file.getOriginalFilename();
        if (originFileName == null) {
            return null;
        }

        String[] fileNameArray = originFileName.split("\\.");
        String imageType = fileNameArray[fileNameArray.length - 1];
        StringBuilder newPathSb = new StringBuilder().append(uploadDir)
                .append(File.separator)
                //这里不能直接用文件名来，而是用UUID来定义文件名字，然后这个UUID要存入数据库，方便后期搜索。
                .append(FileTool.generateFileName())
                .append(".")
                .append(imageType);
        logger.error("newPathString = " + newPathSb);
        //todo 怎么避免重复上传呢？
        //根据原始名称去数据库中搜一下，看是否存在
        return Paths.get(newPathSb.toString());
    }
}
