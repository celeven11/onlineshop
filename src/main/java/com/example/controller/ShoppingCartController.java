package com.example.controller;

import com.example.mapper.ShoppingCartMapper;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.pojo.ShoppingCart;
import com.example.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;


    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    //添加购物车
    @PostMapping("/addcart")
    public Result addcart(@RequestBody ShoppingCart cart) {
        int insert = shoppingCartService.insert(cart);
        if (insert == 0)
            return Result.fail("添加达到上限了！");
        return Result.success("添加成功");
    }

    //删除购物车
    @DeleteMapping("/delcart/{id}")
    public Result delcart(@PathVariable int id) {
        int i = shoppingCartMapper.deleteById(id);
        if (i > 0)
            return Result.success("删除成功");
        return Result.fail("删除失败");
    }

    //批量删除购物车
    @PostMapping("/delallcart")
    public Result delallcart(@RequestBody List<Integer> ids) {
        System.out.println(ids);
        int i = shoppingCartMapper.deleteBatchIds(ids);
        if (i > 0)
            return Result.success("删除成功");
        return Result.fail("删除失败");
    }

    //根据用户获取购物车
    @GetMapping("getcart/{userId}")
    public Result getcart(@PathVariable int userId) {
        PageBean<ShoppingCart> getcart = shoppingCartService.getcart(userId);
        if (getcart == null)
            return Result.fail("赶快去添加商品到购物车吧！");
        return Result.success(getcart);
    }



    //修改购物车
    @PutMapping("updcart")
    public Result updcart(@RequestParam int id, @RequestParam int num){
        int i = shoppingCartService.updcart(id, num);
        if (i == 0)
            return Result.fail("修改失败");
        return Result.success("修改成功");
    }
}
