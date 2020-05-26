package com.shareblog.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ArticlePage
 * @Author 杨武军
 * @Date 2020/5/16 13:12
 *
 * 保存文章查询的条件
 */
@ApiModel("文章分页查询条件")
@Data
public class ArticlePage {
    @ApiModelProperty(value = "页码",required = true)
    private Long page;
    @ApiModelProperty(value = "每一页规格",required = true)
    private Long pageSize;
    @ApiModelProperty("起始时间")
    private String startTime;
    @ApiModelProperty("结束时间")
    private String endTime;
    @ApiModelProperty("标题关键字")
    private String title;
}
