package com.example.service;

import com.example.pojo.PageBean;
import com.example.pojo.ShoppingCart;

public interface ShoppingCartService {

    int insert(ShoppingCart cart);


    PageBean<ShoppingCart> getcart(int userId);

    int updcart(int id, int num);
}
