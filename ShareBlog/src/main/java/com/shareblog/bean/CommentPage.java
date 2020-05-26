package com.shareblog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommentPage
 * @Author 杨武军
 * @Date 2020/5/16 15:56
 */
@ApiModel("评论分页查询条件")
@Data
public class CommentPage extends Comment{
    @ApiModelProperty(value = "页码",required = true)
    private Long page;
    @ApiModelProperty(value = "每一页规格",required = true)
    private Long pageSize;
    @ApiModelProperty("内容关键字")
    private String keys;

}
