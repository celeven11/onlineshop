package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.LikesMapper;
import com.example.pojo.Goods;
import com.example.pojo.Likes;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.GoodsService;
import com.example.service.LikesService;
import com.example.utils.GoodsPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private GoodsPic goodsPic;

    @Autowired
    private GoodsService goodsService;

    @Override
    public PageBean<Goods> getlikes(int userid) {
        QueryWrapper<Likes> wrapper=new QueryWrapper();
        wrapper.eq("user_id",userid);
        List<Likes> likes = likesMapper.selectList(wrapper);
        if(likes.isEmpty()){
            return null;
        }
        List<Goods> goods=new ArrayList<>();
        for (Likes like : likes) {
            int goodsId = like.getGoodsId();
            Goods good = goodsService.getById(goodsId + "");
            goods.add(good);
        }
        PageBean<Goods> g=new PageBean<>(goods.size(),goods);
        return g;
    }

    @Override
    public int addlikes(Likes likes) {
        QueryWrapper<Likes> wrapper=new QueryWrapper();
        wrapper.eq("user_id",likes.getUserId())
                .eq("goods_id",likes.getGoodsId());
        List<Likes> likes1 = likesMapper.selectList(wrapper);
        if(!likes1.isEmpty())
            return 0;
        likesMapper.insert(likes);
        return 1;
    }


}
