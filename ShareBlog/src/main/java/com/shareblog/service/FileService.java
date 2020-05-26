package com.shareblog.service;

import com.shareblog.bean.File;

/**
 * @ClassName FileService
 * @Author 杨武军
 * @Date 2020/5/20 7:40
 */
public interface FileService {
    /**
     * 保存文件
     * @param file
     */
    void saveFile(File file);

    /**
     * 通过id删除文件
    * @param id
     */
    void deleteFile(String id);
}
