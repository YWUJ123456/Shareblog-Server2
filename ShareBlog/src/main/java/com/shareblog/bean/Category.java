package com.shareblog.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("栏目")
@TableName("shareblog_category")
public class Category {
    @ApiModelProperty("编号")
    private Long id;
    @ApiModelProperty(
            value = "栏目名称",
            required = true
    )
    private String name;
    @ApiModelProperty(
            value = "备注",
            required = true
    )
    private String comment;
    @ApiModelProperty(
            value = "序号",
            required = true
    )
    private Integer no;
    @ApiModelProperty("父栏目编号")
    private Long parentId;

}
