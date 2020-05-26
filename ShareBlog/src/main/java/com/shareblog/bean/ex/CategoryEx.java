package com.shareblog.bean.ex;

import com.shareblog.bean.Category;
import lombok.Data;

/**
 * @ClassName CategoryEx
 * @Author 杨武军
 * @Date 2020/5/21 17:33
 * 栏目扩展类 保存本栏目和父栏目信息
 */
@Data
public class CategoryEx {
    private Category category;
    private Category parent;
}
