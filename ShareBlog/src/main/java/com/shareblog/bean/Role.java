package com.shareblog.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName Role
 * @Author 杨武军
 * @Date 2020/5/19 8:40
 */
@Data
@ToString
@TableName("shareblog_roles")
@ApiModel
public class Role {
    @ApiModelProperty("角色id")
    private Integer id;
    @ApiModelProperty("角色名")
    private String name;
}
