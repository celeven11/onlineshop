package com.example.controller;

import com.example.mapper.OrdersMapper;
import com.example.pojo.Orders;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersMapper ordersMapper;

    //添加订单
    @PostMapping("addorder")
    public Result addorder(@RequestBody Orders orders) {
        int addorder = ordersService.addorder(orders);
        if (addorder == 0)
            return Result.fail("库存不足");
        return Result.success("购买成功");
    }

    //获取订单
    @GetMapping("getorder/{userId}")
    public Result getorder(@PathVariable Integer userId) {
        PageBean<Orders> getorder = ordersService.getorder(userId);
        if (getorder == null) {
            return Result.fail("还没下单呢！赶紧去下单吧");
        }
        return Result.success(getorder);
    }

    //根据分类获取订单
    @GetMapping("/getByType")
    public Result getorderByType(@RequestParam Integer userId, @RequestParam Integer state) {
        PageBean<Orders> o = ordersService.getorderByType(userId, state);
        if (o == null) {
            return Result.fail("暂时还没有数据呢");
        }
        return Result.success(o);
    }

    //删除订单
    @DeleteMapping("delorder/{id}")
    public Result delorder(@PathVariable int id) {
        int i = ordersMapper.deleteById(id);
        if(i==0)
            return Result.fail("删除失败");
        return Result.success("删除成功");
    }

    //修改订单
    @PutMapping("/updorder")
    public Result updorder(@RequestBody Map<String,Integer> map){
        int i = ordersService.updorder(map.get("id"), map.get("state"));
        if(i==0)
            return Result.fail("修改失败");
        return Result.success("修改成功");
    }
}
