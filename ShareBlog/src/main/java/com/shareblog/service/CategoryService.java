package com.shareblog.service;

import com.shareblog.bean.Category;
import com.shareblog.bean.ex.CategoryEx;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Author 杨武军
 * @Date 2020/5/16 9:13
 */
public interface CategoryService {
    /**
     * 新增栏目
     * @param category
     */
    void saveCategory(Category category);

    /**
     * 修改栏目信息
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 通过id查询栏目信息
     * @param id
     * @return
     */

    CategoryEx findCategoryById(Long id);
    /**
     * 根据父栏目id查询子栏目信息
     * @param id
     * @return
     */
    List<Category> findCategoryByParentId(Long id);

    /**
     * 通过栏目名查询栏目
     * @return
     */
    CategoryEx findCategoryByCategoryName(String name);

    /**
     * 查询所有栏目信息
     * @return
     */
    List<CategoryEx> findAllCategory();

    /**
     * 通过栏目名删除栏目
     * @param name
     */
    void deleteCategoryByName(String name);

    /**
     * 通过id删除栏目
     * @param id
     */
    void deleteCategoryById(Long id);

    /**
     * 批量删除栏目
     * @param ids
     */
    void batchDeleteCategory(List<Long> ids);
}
