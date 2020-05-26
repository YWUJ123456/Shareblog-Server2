package com.shareblog.controller;

import com.shareblog.bean.Comment;
import com.shareblog.bean.CommentPage;
import com.shareblog.service.CommentService;
import com.shareblog.response.MsgResponse;
import com.shareblog.response.Status;
import com.shareblog.utils.MsgResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CommentController
 * @Author 杨武军
 * @Date 2020/5/16 15:30
 */
@RestController
@RequestMapping("/manager/comment")
@Api(description = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/saveComment")
    @ApiOperation("新增评论")
    public MsgResponse saveComment(@RequestBody Comment comment){
        commentService.saveComment(comment);
        return MsgResponseUtil.addSucess();
    }

    @PutMapping("/updateComment")
    @ApiOperation("修改评论")
    public MsgResponse updateComment(Comment comment){
        commentService.updateComment(comment);
        return MsgResponseUtil.updateSucess();
    }

    @DeleteMapping("/batchDeleteComment")
    @ApiOperation("批量删除评论")
    public MsgResponse batchDeleteComment(Long[] ids){
        commentService.batchDeleteComment(CollectionUtils.arrayToList(ids));
        return MsgResponseUtil.deleteSucess();
    }

    @DeleteMapping("/deleteCommentById")
    @ApiOperation("通过id删除评论")
    public MsgResponse deleteCommentById(Long id){
        commentService.deleteCommentById(id);
        return MsgResponseUtil.deleteSucess();
    }

    @PutMapping("/checkComment")
    @ApiOperation("审核评论")
    public MsgResponse checkComment(Long id){
        commentService.checkComment(id);
        return new MsgResponse(Status.SUCCESS,"审核成功！");
    }

    @GetMapping("/findCommentById")
    @ApiOperation("通过id查询评论")
    public MsgResponse findCommentById(Long id){
        return MsgResponseUtil.findSucess(commentService.findCommentById(id));
    }

    @GetMapping("/findCommentByArticleId")
    @ApiOperation("通过文章id查询评论")
    public MsgResponse findCommentByArticleId(Long articleId){
        return MsgResponseUtil.findSucess(commentService.findCommentByArticleId(articleId));
    }

    @GetMapping("/findCommentByCustomerId")
    @ApiOperation("通过用户id查询评论")
    public MsgResponse findCommentByUserId(Long customerId){
        return MsgResponseUtil.findSucess(commentService.findCommentByUserId(customerId));
    }

    @GetMapping("/findCommentByCondition")
    @ApiOperation("按条件分页查询评论")
    public MsgResponse findCommentByCondition(CommentPage commentPage){
        return MsgResponseUtil.findSucess(commentService.findCommentByCondition(commentPage));
    }
}
