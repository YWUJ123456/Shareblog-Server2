package com.shareblog.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName File
 * @Author 杨武军
 * @Date 2020/5/19 16:15
 */
@Data
@TableName("shareblog_file")
public class File {
    private String id;
    private String fileName;
    private String groupName;
    private String uploadTime;
    private Long fileSize;
}
