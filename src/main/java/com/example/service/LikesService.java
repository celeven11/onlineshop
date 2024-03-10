package com.example.service;

import com.example.pojo.Goods;
import com.example.pojo.Likes;
import com.example.pojo.PageBean;
import com.example.pojo.Result;

public interface LikesService {
    PageBean<Goods> getlikes(int userid);

    int addlikes(Likes likes);


}
