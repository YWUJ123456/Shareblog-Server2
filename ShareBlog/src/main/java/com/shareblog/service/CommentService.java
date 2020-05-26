package com.shareblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shareblog.bean.Comment;
import com.shareblog.bean.CommentPage;
import com.shareblog.bean.ex.CommentEx;

import java.util.List;

/**
 * @ClassName CommentService
 * @Author 杨武军
 * @Date 2020/5/16 15:28
 */
public interface CommentService {
    /**
     * 新增评论
     * @param comment
     */
    void saveComment(Comment comment);

    /**
     * 修改评论
     * @param comment
     */
    void updateComment(Comment comment);
    /**
     * 批量删除评论
     * @param ids
     */
    void batchDeleteComment(List<Long> ids);

    /**
     * 审核评论
     * @param id
     *
     */
    void checkComment(Long id);

    /**
     * 通过id删除评论
     * @param id
     */
    void deleteCommentById(Long id);

    /**
     * 通过id查询评论
     * @param id
     * @return
     */
    CommentEx findCommentById(Long id);

    /**
     * 根据文章id查询评论
     * @param id
     * @return
     */
    List<CommentEx> findCommentByArticleId(Long id);

    /**
     * 通过用户id查询用户的评论
     * @param id
     * @return
     */
    List<CommentEx> findCommentByUserId(Long id);

    /**
     * 通过条件分页查询评论
     * @param commentPage
     * @return
     */
    Page<CommentEx> findCommentByCondition(CommentPage commentPage);
}
