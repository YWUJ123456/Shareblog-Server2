package com.shareblog.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName User
 * @Author 杨武军
 * @Date 2020/5/18 13:12
 */
@Data
@ToString
@ApiModel("用户")
@TableName("shareblog_user")
public class User {
    @ApiModelProperty("用户id")
    @TableId
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("真实姓名")
    private String nickname;
    @ApiModelProperty("密码")
    @JsonIgnore
    private String password;
    @ApiModelProperty("用户状态：true 正常，false 禁用")
    private Boolean enabled;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("用户头像")
    private String userface;
    @ApiModelProperty(value = "注册时间",hidden = true)
    private String regtime;
    @ApiModelProperty("用户角色")
    private Integer roleId;
}
