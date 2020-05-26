package com.shareblog.controller;

import com.shareblog.bean.Article;
import com.shareblog.bean.ArticlePage;
import com.shareblog.response.Status;
import com.shareblog.service.ArticleService;
import com.shareblog.response.MsgResponse;
import com.shareblog.utils.MsgResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ArticleController
 * @Author 杨武军
 * @Date 2020/5/11 16:26
 */
@RequestMapping("/manager/article")
@Api(description = "文章相关接口")
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/saveArticle")
    @ApiOperation("保存文章")
    public MsgResponse save(@RequestBody Article article){
        articleService.save(article);
        return MsgResponseUtil.addSucess();
    }

    @DeleteMapping("/deleteById")
    @ApiOperation("通过id删除文章")
    public MsgResponse deleteById(Long id){
        articleService.deleteById(id);
        return MsgResponseUtil.deleteSucess();
    }

    @DeleteMapping("/batchDelete")
    @ApiOperation("通过id批量删除")
    public MsgResponse batchDelete(Long[] ids){
        articleService.batchDelete(ids);
        return MsgResponseUtil.deleteSucess();
    }

    @PutMapping("/updateArticle")
    @ApiOperation("修改文章")
    public MsgResponse update(@RequestBody Article article){
        articleService.update(article);
        return MsgResponseUtil.updateSucess();
    }

    @PutMapping("/checkArticle")
    @ApiOperation("审核文章")
    public MsgResponse checkArticle(Long id){
        articleService.checkArticle(id);
        return MsgResponseUtil.updateSucess();
    }

    @GetMapping("/findArticleById")
    @ApiOperation("通过id查询文章")
    public MsgResponse findById(Long id){
        return MsgResponseUtil.findSucess(articleService.findById(id));
    }

    @GetMapping("/findAllArticle")
    @ApiOperation("查询所有文章")
    public MsgResponse findAll(){
        return MsgResponseUtil.findSucess(articleService.findAll());
    }

    @GetMapping("/findArticleByCondition")
    @ApiOperation(value = "根据条件分页查询",notes = "时间格式：yyyy-mm-dd")
    public MsgResponse findByCondition(ArticlePage articlePage){
        return MsgResponseUtil.findSucess(articleService.findByCondition(articlePage));
    }

    @PutMapping("/thumbUp")
    @ApiOperation("点赞文章")
    public MsgResponse thumbUp(Long articleId,Long userId){
        articleService.thumbUp(articleId,userId);
        return new MsgResponse(Status.SUCCESS,"点赞成功");
    }
    @PutMapping("/thumbDown")
    @ApiOperation("鄙视文章")
    public MsgResponse thumbDown(Long articleId,Long userId){
        articleService.thumbDown(articleId,userId);
        return new MsgResponse(Status.SUCCESS,"鄙视成功");
    }

    @GetMapping("/findArticleByCategoryId")
    @ApiOperation("通过栏目id查询所有文章")
    public MsgResponse findArticleByCategoryId(Long categoryId){
        return MsgResponseUtil.findSucess(articleService.findByCategoryId(categoryId));
    }

    @GetMapping("/findArticleByUserId")
    @ApiOperation("通过用户id查询所有文章")
    public MsgResponse findArticleByUserId(Long userId){
        return MsgResponseUtil.findSucess(articleService.findByAuthorId(userId));
    }

}
