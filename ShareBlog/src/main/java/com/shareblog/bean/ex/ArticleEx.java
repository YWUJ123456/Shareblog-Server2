package com.shareblog.bean.ex;

import com.shareblog.bean.Article;
import com.shareblog.bean.Category;
import com.shareblog.bean.User;
import lombok.Data;

/**
 * @ClassName ArticleEx
 * @Author 杨武军
 * @Date 2020/5/21 18:11
 * 文章扩展类 保存文章，栏目，用户信息用于返回给前端
 */
@Data
public class ArticleEx {
    private Article article;
    private User author;
    private Category category;
}
