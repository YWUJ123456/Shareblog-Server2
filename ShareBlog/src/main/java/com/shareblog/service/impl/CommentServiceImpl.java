package com.shareblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shareblog.bean.Comment;
import com.shareblog.bean.CommentPage;
import com.shareblog.bean.ex.CommentEx;
import com.shareblog.mapper.ArticleMapper;
import com.shareblog.mapper.CommentMapper;
import com.shareblog.mapper.UserMapper;
import com.shareblog.service.CommentService;
import com.shareblog.exception.BaseException;
import com.shareblog.response.Status;
import com.shareblog.utils.ExceptionUtil;
import com.shareblog.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CommentServiceImpl
 * @Author 杨武军
 * @Date 2020/5/16 15:29
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    @Transactional
    public void saveComment(Comment comment) {
        comment.setId(idWorker.nextId());
        comment.setCommenttime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        comment.setStatus("未审核");
        commentMapper.insert(comment);
    }

    @Override
    @Transactional
    public void updateComment(Comment comment) {
        if (comment.getId()==null){
            ExceptionUtil.idIsNull();
        }
        commentMapper.updateById(comment);
    }

    @Override
    @Transactional
    public void batchDeleteComment(List<Long> ids) {
        if (ids.size()<=0){
          throw new BaseException(Status.FAILED,"没有选择任何要删除的评论！");
        }
        commentMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional
    public void checkComment(Long id) {
        Comment comment = commentMapper.selectById(id);
        comment.setStatus("审核通过");
        commentMapper.updateById(comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long id) {
        commentMapper.deleteById(id);
    }

    @Override
    public CommentEx findCommentById(Long id) {
        Comment comment = commentMapper.selectById(id);
        CommentEx commentEx = new CommentEx();
        commentEx.setComment(comment);
        commentEx.setArticle(articleMapper.selectById(comment.getArticleId()));
        commentEx.setUser(userMapper.selectById(comment.getCustomerId()));
        return commentEx;
    }

    @Override
    public List<CommentEx> findCommentByArticleId(Long id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq(id!=null,"article_id",id);
        List<Comment> comments = commentMapper.selectList(wrapper);
        List<CommentEx> commentExes = new ArrayList<>();
        CommentEx commentEx = null;
        for (Comment comment : comments) {
            commentEx.setComment(comment);
            commentEx.setArticle(articleMapper.selectById(comment.getArticleId()));
            commentEx.setUser(userMapper.selectById(comment.getCustomerId()));
            commentExes.add(commentEx);
        }
        return commentExes;
    }

    @Override
    public List<CommentEx> findCommentByUserId(Long id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq(id!=null,"customer_id",id);
        final List<Comment> comments = commentMapper.selectList(wrapper);
        List<CommentEx> commentExes = new ArrayList<>();
        CommentEx commentEx = null;
        for (Comment comment : comments) {
            commentEx.setComment(comment);
            commentEx.setArticle(articleMapper.selectById(comment.getArticleId()));
            commentEx.setUser(userMapper.selectById(comment.getCustomerId()));
            commentExes.add(commentEx);
        }
        return commentExes;
    }

    @Override
    public Page<CommentEx> findCommentByCondition(CommentPage commentPage) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq(commentPage.getArticleId()!=null,"article_id",commentPage.getArticleId());
        wrapper.eq(commentPage.getCustomerId()!=null,"customer_id",commentPage.getCustomerId());
        wrapper.like(commentPage.getKeys()!=null,"content",commentPage.getKeys());
        wrapper.eq(commentPage.getStatus()!=null,"status",commentPage.getStatus());
        Page<Comment> page = new Page<>(commentPage.getPage(),commentPage.getPageSize());
        Page<Comment> commentPages = commentMapper.selectPage(page, wrapper);

        Page<CommentEx> commentExPage = new Page<>();
        List<CommentEx> commentExes = new ArrayList<>();
        CommentEx commentEx = null;
        for (Comment comment : commentPages.getRecords()) {
            commentEx = new CommentEx();
            commentEx.setComment(comment);
            commentEx.setArticle(articleMapper.selectById(comment.getArticleId()));
            commentEx.setUser(userMapper.selectById(comment.getCustomerId()));
            commentExes.add(commentEx);
        }
        commentExPage.setRecords(commentExes);
        commentExPage.setCurrent(commentPages.getCurrent());
        commentExPage.setTotal(commentPages.getTotal());
        commentExPage.setPages(commentPages.getPages());
        commentExPage.setSize(commentPages.getSize());

        return commentExPage;
    }
}
