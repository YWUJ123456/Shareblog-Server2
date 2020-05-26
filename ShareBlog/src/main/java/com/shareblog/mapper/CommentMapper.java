package com.shareblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shareblog.bean.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName CommentMapper
 * @Author 杨武军
 * @Date 2020/5/16 15:30
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
