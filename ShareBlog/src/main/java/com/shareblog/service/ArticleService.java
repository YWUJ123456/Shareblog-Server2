package com.shareblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shareblog.bean.Article;
import com.shareblog.bean.ArticlePage;
import com.shareblog.bean.ex.ArticleEx;

import java.util.List;

/**
 * @ClassName ArticleMapper
 * @Author 杨武军
 * @Date 2020/5/11 16:31
 */
public interface ArticleService {
    /**
     * 保存文章
     * @param article
     */
    void save(Article article);

    /**
     * 通过id删除文章
     * @param id
     */
    void deleteById(Long id);
    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(Long[] ids);

    /**
     * 修改文章
     * @param article
     */
    void update(Article article);

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    ArticleEx findById(Long id);

    /**
     * 查询所有
     * @return
     */
    List<ArticleEx> findAll();


    /**
     * 审核文章
     * @param id
     */
    void checkArticle(Long id);

    /**
     * 通过条件查询
     * @param articlePage
     * @return
     */
    Page<ArticleEx> findByCondition(ArticlePage articlePage);

    /**
     * 通过栏目id查询文章
     * @param id
     * @return
     */
    List<Article> findByCategoryId(Long id);

    /**
     * 通过作者id查询文章
     * @param id
     * @return
     */
    List<Article> findByAuthorId(Long id);

    /**
     * 点赞文章
     */
    void thumbUp(Long articleId,Long userId);

    /**
     * 鄙视文章
     */
    void thumbDown(Long articleId,Long userId);


}
