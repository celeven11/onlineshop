package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.GoodsMapper;
import com.example.mapper.ShoppingCartMapper;
import com.example.pojo.Goods;
import com.example.pojo.PageBean;
import com.example.pojo.ShoppingCart;
import com.example.service.ShoppingCartService;
import com.example.utils.GoodsPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private GoodsPic goodsPic;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int insert(ShoppingCart cart) {
        QueryWrapper<ShoppingCart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", cart.getUserId());
        wrapper.eq("goods_id", cart.getGoodsId());
        ShoppingCart curcart = shoppingCartMapper.selectOne(wrapper);
        if (curcart != null) {
            Goods goods = goodsMapper.selectById(cart.getGoodsId());
            // 判断库存
            int totalnum = curcart.getNum() + cart.getNum();
            // 为0时删除购物车
            if (totalnum <= 0){
                shoppingCartMapper.deleteById(curcart.getId());
                return 1;
            }
            if (totalnum > goods.getNum())
                return 0;
            curcart.setNum(totalnum);
            shoppingCartMapper.updateById(curcart);
            return 1;
        }
        shoppingCartMapper.insert(cart);
        return 1;
    }

    @Override
    public PageBean<ShoppingCart> getcart(int userId) {
        QueryWrapper<ShoppingCart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<ShoppingCart> carts = shoppingCartMapper.selectList(wrapper);
        if (carts.isEmpty())
            return null;
        for (ShoppingCart cart : carts) {
            Goods goods = goodsMapper.selectById(cart.getGoodsId());
            goodsPic.getImg(goods);
            cart.setGoods(goods);
        }
        PageBean<ShoppingCart> c = new PageBean<>(carts.size(), carts);
        return c;
    }

    @Override
    public int updcart(int id, int num) {
        ShoppingCart cart = shoppingCartMapper.selectById(id);
        if(cart==null)
            return 0;
        Goods goods = goodsMapper.selectById(cart.getGoodsId());
        if(goods.getNum() < num || num<= 0)
            return 0;
        cart.setNum(num);
        shoppingCartMapper.updateById(cart);
        return 1;
    }
}
