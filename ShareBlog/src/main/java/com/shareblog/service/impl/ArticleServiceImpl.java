package com.shareblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shareblog.bean.Article;
import com.shareblog.bean.ArticlePage;
import com.shareblog.bean.ex.ArticleEx;
import com.shareblog.exception.BaseException;
import com.shareblog.mapper.ArticleMapper;
import com.shareblog.mapper.CategoryMapper;
import com.shareblog.mapper.UserMapper;
import com.shareblog.response.Status;
import com.shareblog.service.ArticleService;
import com.shareblog.utils.ExceptionUtil;
import com.shareblog.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ArticleMapper
 * @Author 杨武军
 * @Date 2020/5/11 16:30
 */

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public void save(Article article) {
        article.setId(idWorker.nextId());
        article.setThumbdown(0);
        article.setThumbup(0);
        article.setStatus("未审核");
        article.setReadtimes(0);
        article.setPublishtime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        articleMapper.insert(article);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        articleMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void batchDelete(Long[] ids) {
        for (Long id : ids) {
           articleMapper.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void update(Article article) {
        if(article.getId()==null){
            ExceptionUtil.idIsNull();
        }
        articleMapper.updateById(article);
    }

    @Override
    public ArticleEx findById(Long id) {
        Article article = articleMapper.selectById(id);
        ArticleEx articleEx = new ArticleEx();
        articleEx.setArticle(article);
        articleEx.setCategory(categoryMapper.selectById(article.getCategoryId()));
        articleEx.setAuthor(userMapper.selectById(article.getUserId()));
        return articleEx;
    }

    @Override
    public List<ArticleEx> findAll() {
        List<Article> articles = articleMapper.selectList(null);
        List<ArticleEx> articleExes = new ArrayList<>();
        ArticleEx articleEx = null;
        //遍历所有文章 封装
        for (Article article : articles) {
            articleEx = new ArticleEx();
            articleEx.setArticle(article);
            articleEx.setCategory(categoryMapper.selectById(article.getCategoryId()));
            articleEx.setAuthor(userMapper.selectById(article.getUserId()));
            articleExes.add(articleEx);
        }
        return articleExes;
    }

    @Override
    @Transactional
    public void checkArticle(Long id) {
        if (id==null){
            ExceptionUtil.idIsNull();
        }
        Article article =  articleMapper.selectById(id);
        article.setStatus("审核通过");
        articleMapper.updateById(article);
    }

    @Override
    public Page<ArticleEx> findByCondition(ArticlePage articlePage) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
            wrapper.like(articlePage.getTitle()!=null,"title",articlePage.getTitle());
        if (!StringUtils.isEmpty(articlePage.getStartTime())){
            if (!StringUtils.isEmpty(articlePage.getEndTime())){
                wrapper.apply("publishTime >= CONCAT('"+articlePage.getStartTime()+"',' 00:00:00') and publishTime <= CONCAT('','"+articlePage.getEndTime()+"',' 23:59:59')");
            }else {
                wrapper.apply("publishTime >= CONCAT('"+articlePage.getStartTime()+"',' 00:00:00')");
            }
        }else{
            if (!StringUtils.isEmpty(articlePage.getEndTime())){
                wrapper.apply("publishTime <= CONCAT('"+articlePage.getEndTime()+"',' 23:59:59')");
            }
        }
        Page<Article> page = new Page<>(articlePage.getPage(),articlePage.getPageSize());
        Page<Article> articlePages = articleMapper.selectPage(page, wrapper);
        //将结果封装为ArticleEx
        Page<ArticleEx> articleExPage = new Page<>();
        List<ArticleEx> articleExes = new ArrayList<>();
        ArticleEx articleEx = null;
        for (Article article : articlePages.getRecords()) {
            articleEx = new ArticleEx();
            articleEx.setArticle(article);
            articleEx.setCategory(categoryMapper.selectById(article.getCategoryId()));
            articleEx.setAuthor(userMapper.selectById(article.getUserId()));
            articleExes.add(articleEx);
        }
        //分页信息设置
        articleExPage.setRecords(articleExes);
        articleExPage.setPages(articlePages.getPages());
        articleExPage.setCurrent(articlePages.getCurrent());
        articleExPage.setSize(articlePages.getSize());
        articleExPage.setTotal(articlePages.getTotal());
        return articleExPage;
    }

    @Override
    public List<Article> findByCategoryId(Long id) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id",id);
        return articleMapper.selectList(wrapper);
    }

    @Override
    public List<Article> findByAuthorId(Long id) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        return articleMapper.selectList(wrapper);
    }

    @Override
    @Transactional
    public void thumbUp(Long articleId,Long userId) {
        String key = articleId+"+"+userId;
        if (redisTemplate.opsForValue().get(key)!=null){
            throw new BaseException(Status.FAILED,"不能重复点赞！");
        }
        redisTemplate.opsForValue().set(key,"");
        Article article = articleMapper.selectById(articleId);
        article.setThumbup(article.getThumbup()+1);
        update(article);
    }

    @Override
    @Transactional
    public void thumbDown(Long articleId,Long userId) {
        String key = articleId+"-"+userId;
        if (redisTemplate.opsForValue().get(key)!=null){
            throw new BaseException(Status.FAILED,"不能重复鄙视！");
        }
        redisTemplate.opsForValue().set(key,"");
        Article article = articleMapper.selectById(articleId);
        article.setThumbup(article.getThumbdown()+1);
        update(article);
    }
}
