package com.shareblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shareblog.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Author 杨武军
 * @Date 2020/5/18 13:29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
