package com.shareblog.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName FastDFSClientWrapper
 * @Author 杨武军
 * @Date 2020/5/19 19:18
 */
@Component
public class FastDFSUtil {
    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    public String[] uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        String[] fileMsg = new String[2];
        fileMsg[0] = storePath.getFullPath();
        fileMsg[1] = storePath.getGroup();
        return fileMsg;
    }

    /**
     * 删除文件
     * @param fileUrl
     */
    public void deleteFile(String fileUrl){
        storageClient.deleteFile(fileUrl);
    }


}
