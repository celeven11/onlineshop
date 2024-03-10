package com.example.controller;

import com.example.mapper.CategoryMapper;
import com.example.pojo.Category;
import com.example.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;

    //获取所有分类
    @GetMapping("/get")
    public Result<List<Category>> getCategory() {
        List<Category> categories = categoryMapper.selectList(null);
        return Result.success(categories); 
    }
}
