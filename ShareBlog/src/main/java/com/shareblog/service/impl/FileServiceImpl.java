package com.shareblog.service.impl;

import com.shareblog.bean.File;
import com.shareblog.mapper.FileMapper;
import com.shareblog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName FileServiceImpl
 * @Author 杨武军
 * @Date 2020/5/20 7:40
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    @Transactional
    public void saveFile(File file) {
        fileMapper.insert(file);
    }

    @Override
    @Transactional
    public void deleteFile(String id) {
        fileMapper.deleteById(id);
    }
}
