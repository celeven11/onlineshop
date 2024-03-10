package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.AddressMapper;
import com.example.mapper.GoodsMapper;
import com.example.mapper.OrdersMapper;
import com.example.pojo.Address;
import com.example.pojo.Goods;
import com.example.pojo.Orders;
import com.example.pojo.PageBean;
import com.example.service.OrdersService;
import com.example.utils.GoodsPic;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private GoodsPic goodsPic;

    @Override
    public int addorder(Orders orders) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusMinutes(15);
        Goods goods = goodsMapper.selectById(orders.getGoodsId());
        if (goods.getNum() < orders.getNum())
            return 0;
        goods.setNum(goods.getNum() - orders.getNum());
        goodsMapper.updateById(goods);
        orders.setOrderTime(now);
        orders.setEndTime(end);
        ordersMapper.insert(orders);
        return 1;
    }

    @Override
    public PageBean<Orders> getorder(Integer userId) {
        QueryWrapper<Orders> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        wrapper.orderByDesc("order_id");
        List<Orders> list = ordersMapper.selectList(wrapper);
        if (list.isEmpty())
            return null;
        for (Orders order : list) {
            Goods goods = goodsMapper.selectById(order.getGoodsId());
            goodsPic.getImg(goods);
            order.setGoods(goods);
            Address address = addressMapper.selectById(order.getAddressId());
            order.setAddress(address);
        }
        PageBean<Orders> o = new PageBean<>(list.size(), list);
        return o;
    }

    @Override
    public PageBean<Orders> getorderByType(Integer userId, Integer state) {
        QueryWrapper<Orders> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId).eq("state", state);
        wrapper.orderByDesc("order_id");

        List<Orders> orders = ordersMapper.selectList(wrapper);
        if (orders.isEmpty())
            return null;
        for (Orders order : orders) {
            Goods goods = goodsMapper.selectById(order.getGoodsId());
            goodsPic.getImg(goods);
            order.setGoods(goods);
            Address address = addressMapper.selectById(order.getAddressId());
            order.setAddress(address);
        }
        PageBean<Orders> o = new PageBean<>(orders.size(), orders);
        return o;
    }

    @Override
    public int updorder(int id, Integer state) {
        Orders orders = ordersMapper.selectById(id);
        if(orders==null)
            return 0;
        orders.setState(state);
        ordersMapper.updateById(orders);
        return 1;
    }
}
