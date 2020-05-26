package com.shareblog.controller;

import com.shareblog.response.MsgResponse;
import com.shareblog.response.Status;
import com.shareblog.bean.File;
import com.shareblog.service.FileService;
import com.shareblog.utils.FastDFSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName FileController
 * @Author 杨武军
 * @Date 2020/5/19 17:52
 */
@RestController
@RequestMapping("/manager/file")
@Api(description = "文件相关接口")
public class FileController {
    @Autowired
    private FastDFSUtil dfsClient;
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传",
                   notes = "文件大小限制为50M,文件服务器地址：http://120.24.21.21:8888，图片地址：文件服务器地址+文件id")
    public MsgResponse upload(@RequestParam("file")MultipartFile file){
        File dfsFile = new File();
        try {
            String[] fileMsg = dfsClient.uploadFile(file);
            dfsFile.setId(fileMsg[0]);
            dfsFile.setFileName(file.getOriginalFilename());
            dfsFile.setFileSize(file.getSize());
            dfsFile.setGroupName(fileMsg[1]);
            dfsFile.setUploadTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            fileService.saveFile(dfsFile);
        } catch (Exception e) {
            e.printStackTrace();
            return new MsgResponse(Status.FAILED,"上传失败");
        }
        return new MsgResponse(Status.SUCCESS,"上传成功",dfsFile);
    }

    @GetMapping("/delete")
    @ApiOperation("删除文件")
    public MsgResponse delete(String id){
        fileService.deleteFile(id);
        dfsClient.deleteFile("http://120.24.21.21:8888/"+id);
        return new MsgResponse(Status.SUCCESS,"删除成功");
    }

}
