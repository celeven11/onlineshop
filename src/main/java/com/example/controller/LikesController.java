package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.LikesMapper;
import com.example.pojo.Goods;
import com.example.pojo.Likes;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("likes")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @Autowired
    private LikesMapper likesMapper;

    //获取用户收藏
    @GetMapping("getlikes/{userid}")
    public Result getlikes(@PathVariable int userid) {
        PageBean<Goods> goods = likesService.getlikes(userid);
        if (goods == null) {
            return Result.fail("暂时还没收藏呢");
        }
        return Result.success(goods);
    }

    //添加收藏
    @PostMapping("addlikes")
    public Result addlikes(@RequestBody Likes likes) {
        int addlikes = likesService.addlikes(likes);
        if(addlikes > 0)
            return Result.success("收藏成功");
        return Result.fail("已经收藏过了！");
    }

    //取消收藏
    @DeleteMapping("deletelikes/{userid}/{goodsid}")
    public Result deletelikes(@PathVariable int userid, @PathVariable int goodsid){
        QueryWrapper wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userid);
        wrapper.eq("goods_id",goodsid);
        int i = likesMapper.delete(wrapper);
        if(i>0){
            return Result.success("取消收藏成功");
        }
        return Result.fail("取消收藏失败");
    }
}
