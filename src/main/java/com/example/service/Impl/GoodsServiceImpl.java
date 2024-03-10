package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.GoodsMapper;
import com.example.pojo.Goods;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.GoodsService;
import com.example.utils.GoodsPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsPic goodsPic;

    /*
    获取所有商品*/
    @Override
    public PageBean<Goods> getAll(int current, int size) {
        Page<Goods> p=new Page<>(current,size);
        IPage<Goods> goods = goodsMapper.selectPage(p, null);
        if (goods.getTotal()==0) {
            return null;
        }
        // 获取图片
        goodsPic.getImgs(goods.getRecords());
        PageBean<Goods> g=new PageBean<>((int)goods.getTotal(),goods.getRecords());
        return g;
    }

    @Override
    public PageBean<Goods> getByCategory(String categoryid,int current,int size) {
        Page<Goods> p=new Page<>(current,size);
        QueryWrapper<Goods> wrapper = new QueryWrapper();
        wrapper.eq("category_id", categoryid);
        Page<Goods> goodsPage = goodsMapper.selectPage(p, wrapper);
        if (goodsPage.getTotal()==0) {
            return null;
        }
        //获取照片
        goodsPic.getImgs(goodsPage.getRecords());
        PageBean<Goods> g=new PageBean<>((int)goodsPage.getTotal(),goodsPage.getRecords());
        return g;
    }

    @Override
    public PageBean<Goods> query(String word, int current, int size) {
        Page<Goods> p=new Page<>(current,size);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("goods_name", word).or()
                .like("description",word);
        IPage<Goods> goods = goodsMapper.selectPage(p, wrapper);
        if(goods.getTotal()==0){
            return null;
        }
        goodsPic.getImgs(goods.getRecords());
        PageBean<Goods> g=new PageBean<>((int)goods.getTotal(),goods.getRecords());
        return g;
    }

    @Override
    public Goods getById(String id) {
        Goods good = goodsMapper.selectById(id);
        if(good==null){
            return null;
        }
        goodsPic.getImg(good);
        return good;
    }

    @Override
    public List<Goods> getHot() {
        Integer[] a={1,3,6,19,23,29,35,36,40,51,53,56,61,64,65,66,68,72,73,81,};
        List<Integer> ids = Arrays.asList(a);
        List<Goods> goodsList = goodsMapper.selectBatchIds(ids);
        goodsPic.getImgs(goodsList);
        return goodsList;
    }
}
