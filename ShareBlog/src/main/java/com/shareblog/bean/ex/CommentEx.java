package com.shareblog.bean.ex;

import com.shareblog.bean.Article;
import com.shareblog.bean.Comment;
import com.shareblog.bean.User;
import lombok.Data;

/**
 * @ClassName CommentEx
 * @Author 杨武军
 * @Date 2020/5/21 18:57
 * 评论扩展类
 */
@Data
public class CommentEx {
    private Comment comment;
    private Article article;
    private User user;
}
