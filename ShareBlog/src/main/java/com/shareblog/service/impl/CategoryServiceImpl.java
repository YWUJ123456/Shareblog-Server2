package com.shareblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shareblog.bean.Category;
import com.shareblog.bean.ex.CategoryEx;
import com.shareblog.mapper.CategoryMapper;
import com.shareblog.service.CategoryService;
import com.shareblog.exception.BaseException;
import com.shareblog.response.Status;
import com.shareblog.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Author 杨武军
 * @Date 2020/5/16 9:15
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private IdWorker idWorker;
    @Override
    @Transactional
    public void saveCategory(Category category) {
        category.setId(idWorker.nextId());
        if(findCategoryByCategoryName(category.getName()).getCategory()!=null){
            throw new BaseException(Status.FAILED,"栏目名已存在！");
        }
        categoryMapper.insert(category);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        if(category.getId()==null){
            throw new BaseException(Status.FAILED,"更新失败，栏目id为空！");
        }
        Category own = categoryMapper.selectById(category.getId());
        if (!own.getName().equals(category.getName())){
            if (findCategoryByCategoryName(category.getName()).getCategory()!=null){
                throw new BaseException(Status.FAILED,"栏目已存在！");
            }
        }
        categoryMapper.updateById(category);
    }

    @Override
    public CategoryEx findCategoryById(Long id) {
        Category category = categoryMapper.selectById(id);
        CategoryEx categoryEx = new CategoryEx();
        categoryEx.setCategory(category);
        categoryEx.setParent(categoryMapper.selectById(category.getParentId()));
        return categoryEx;
    }

    @Override
    public List<Category> findCategoryByParentId(Long id) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        return categoryMapper.selectList(wrapper);
    }

    @Override
    public CategoryEx findCategoryByCategoryName(String name) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Category category = categoryMapper.selectOne(wrapper);
        CategoryEx categoryEx = new CategoryEx();
        categoryEx.setCategory(category);
        if (category!=null){
            categoryEx.setParent(categoryMapper.selectById(category.getParentId()));
        }
        return categoryEx;
    }

    @Override
    public List<CategoryEx> findAllCategory() {
        List<Category> categories = categoryMapper.selectList(null);
        List<CategoryEx> categoryExes = new ArrayList<>();
        CategoryEx categoryEx = null;
        for (Category category : categories) {
            categoryEx = new CategoryEx();
            categoryEx.setCategory(category);
            categoryEx.setParent(categoryMapper.selectById(category.getParentId()));
            categoryExes.add(categoryEx);
        }
        return categoryExes;
    }

    @Override
    @Transactional
    public void deleteCategoryByName(String name) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        categoryMapper.delete(wrapper);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        categoryMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void batchDeleteCategory(List<Long> ids) {
        categoryMapper.deleteBatchIds(ids);
    }
}
