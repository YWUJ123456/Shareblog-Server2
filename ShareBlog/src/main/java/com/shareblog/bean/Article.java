package com.shareblog.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * @ClassName Article
 * @Author 杨武军
 * @Date 2020/5/11 16:23
 */
@Data
@ToString
@TableName("shareblog_article")
@ApiModel("文章")
public class Article implements Serializable {
    @ApiModelProperty("编号")
    private Long id;
    @ApiModelProperty(
            value = "标题",
            required = true
    )
    private String title;
    @ApiModelProperty(
            value = "列表样式",
            required = true
    )
    private String liststyle;
    @ApiModelProperty("发布时间")
    private String publishtime;
    @ApiModelProperty("阅读次数")
    private Integer readtimes;
    @ApiModelProperty("审核状态")
    private String status;
    @ApiModelProperty("点赞次数")
    private Integer thumbup;
    @ApiModelProperty("鄙视次数")
    private Integer thumbdown;
    @ApiModelProperty("音乐地址")
    private String music;
    @ApiModelProperty("视频地址")
    private String video;
    @ApiModelProperty("文章封面")
    private String source;
    @ApiModelProperty("文章内容")
    private String content;
    private Integer userId;
    private Long categoryId;
}
