package com.example.controller;

import com.example.pojo.Goods;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.GoodsService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;


    //获取轮播图
    @GetMapping("/carousel")
    public Result Carousel() {
        List<String> list = new ArrayList();
        list.add("http://localhost:8888/static/imgs/1/6/主图-01.jpg");
        list.add("http://localhost:8888/static/imgs/3/53/主图-02.png");
        list.add("http://localhost:8888/static/imgs/4/71/主图-02.jpg");
        list.add("http://localhost:8888/static/imgs/4/80/主图-08.jpg");
        return Result.success(list);
    }


    //获取热门商品
    @GetMapping("getHot")
    public Result getHot() {
        List<Goods> hot = goodsService.getHot();
        return Result.success(hot);
    }


    //获取所有商品
    @GetMapping("getAll")
    public Result getAll1( @RequestParam(defaultValue = "10") int current,
                           @RequestParam(defaultValue = "12") int size) {
        PageBean<Goods> all = goodsService.getAll(current,size);
        if (all != null)
            return Result.success(all);
        return Result.fail("抱歉！没有找到相关商品");
    }

    //根据分类获取商品
    @GetMapping("getByCategory/{categoryid}")
    public Result getByCategory(@PathVariable String categoryid,
                                @RequestParam(defaultValue = "1") int current,
                                @RequestParam(defaultValue = "12") int size) {
        PageBean<Goods> goods = goodsService.getByCategory(categoryid, current, size);
        if (goods != null)
            return Result.success(goods);
        return Result.fail("抱歉！没有找到相关商品");
    }

    //获取单件商品
    @GetMapping("getById/{id}")
    public Result getById(@PathVariable String id) {
        Goods goods = goodsService.getById(id);
        if (goods != null)
            return Result.success(goods);
        return Result.fail("抱歉！没有找到相关商品");

    }

    //根据内容查询商品
    @GetMapping("query/{word}")
    public Result query(@PathVariable String word,
                         @RequestParam(defaultValue = "1") int current,
                        @RequestParam(defaultValue = "12") int size) {
        PageBean<Goods> query = goodsService.query(word,current,size);
        if (query != null) {
            return Result.success(query);
        }
        return Result.fail("抱歉！没有找到相关商品");
    }

}
