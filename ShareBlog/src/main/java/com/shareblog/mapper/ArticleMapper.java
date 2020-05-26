package com.shareblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shareblog.bean.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName ArticleMapper
 * @Author 杨武军
 * @Date 2020/5/11 16:28
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
