package com.shareblog.controller;

import com.shareblog.bean.Category;
import com.shareblog.service.CategoryService;
import com.shareblog.response.MsgResponse;
import com.shareblog.utils.MsgResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CategoryController
 * @Author 杨武军
 * @Date 2020/5/11 17:41
 */
@Api(description = "栏目相关接口")
@RestController
@RequestMapping("/manager/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/saveCategory")
    @ApiOperation("新增栏目")
    public MsgResponse saveCategory(Category category){
        categoryService.saveCategory(category);
        return MsgResponseUtil.addSucess();
    }

    @PutMapping("/updateCategory")
    @ApiOperation("修改栏目信息")
    public MsgResponse updateCategory(Category category){
        categoryService.updateCategory(category);
        return MsgResponseUtil.updateSucess();
    }

    @GetMapping("/findCategoryById")
    @ApiOperation("通过id查询栏目信息")
    public MsgResponse findCategoryById(Long id){
        return MsgResponseUtil.findSucess(categoryService.findCategoryById(id));
    }

    @GetMapping("/findCategoryByName")
    @ApiOperation("通过栏目名查询栏目信息")
    public MsgResponse findCategoryByCategoryName(String name){
        return MsgResponseUtil.findSucess(categoryService.findCategoryByCategoryName(name));
    }

    @GetMapping("/findAllCategory")
    @ApiOperation("查询所有栏目信息")
    public MsgResponse findAllCategory(){
        return MsgResponseUtil.findSucess(categoryService.findAllCategory());
    }

    @GetMapping("/findCategoryByParentId")
    @ApiOperation("通过父栏目id查询子栏目信息")
    public MsgResponse findCategoryByParentId(Long id){
        return MsgResponseUtil.findSucess(categoryService.findCategoryByParentId(id));
    }

    @DeleteMapping("/deleteCategoryByName")
    @ApiOperation("通过栏目名删除栏目")
    public MsgResponse deleteCategoryByName(String name){
        categoryService.deleteCategoryByName(name);
        return MsgResponseUtil.deleteSucess();
    }

    @DeleteMapping("/deleteCategoryById")
    @ApiOperation("通过id删除栏目信息")
    public MsgResponse deleteCategoryById(Long id){
        categoryService.deleteCategoryById(id);
        return MsgResponseUtil.deleteSucess();
    }

    @DeleteMapping("/batchDeleteCategory")
    @ApiOperation("批量删除栏目信息")
    public MsgResponse batchDeleteCategory(Long[] ids){
        categoryService.batchDeleteCategory(CollectionUtils.arrayToList(ids));
        return MsgResponseUtil.deleteSucess();
    }
}
