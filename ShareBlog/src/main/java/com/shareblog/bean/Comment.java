package com.shareblog.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName Comment
 * @Author 杨武军
 * @Date 2020/5/16 15:31
 */
@Data
@ToString
@TableName("shareblog_comment")
@ApiModel("评论内容")
public class Comment {
    @ApiModelProperty("评论id")
    private Long id;
    @ApiModelProperty("评论时间")
    private String commenttime;
    @ApiModelProperty("评论状态")
    private String status;
    @ApiModelProperty("文章id")
    private Long articleId;
    @ApiModelProperty("用户id")
    private Integer customerId;
    @ApiModelProperty("评论内容")
    private String content;
}
