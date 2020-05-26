package com.shareblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shareblog.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CategoryMapper
 * @Author 杨武军
 * @Date 2020/5/13 8:57
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
