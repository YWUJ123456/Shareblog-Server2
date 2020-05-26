package com.shareblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shareblog.bean.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName RoleMapper
 * @Author 杨武军
 * @Date 2020/5/19 8:42
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
