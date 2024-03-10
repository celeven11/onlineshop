package com.example.service;

import com.example.pojo.Goods;
import com.example.pojo.PageBean;
import com.example.pojo.Result;

import java.io.IOException;
import java.util.List;

public interface GoodsService {
    PageBean<Goods> getAll(int current, int size);

    PageBean<Goods> getByCategory(String categoryid,int current,int size);

    PageBean<Goods> query(String word, int current, int size);

    Goods getById(String id);

    List<Goods> getHot();
}
