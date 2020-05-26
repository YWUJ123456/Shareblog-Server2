package com.shareblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shareblog.bean.File;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName FileMapper
 * @Author 杨武军
 * @Date 2020/5/20 7:39
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {
}
