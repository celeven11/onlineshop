package com.example.service;

import com.example.pojo.Orders;
import com.example.pojo.PageBean;

public interface OrdersService {
    int addorder(Orders orders);

    PageBean<Orders> getorder(Integer userId);

    PageBean<Orders> getorderByType(Integer userId, Integer state);

    int updorder(int id, Integer state);
}
