package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.AddressMapper;
import com.example.pojo.Address;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressService addressService;



    //添加地址
    @PostMapping("add")
    public Result add(@RequestBody Address address) {
        int insert = addressMapper.insert(address);
        if (insert > 0)
            return Result.success();
        return Result.fail();
    }

    @GetMapping("/get")
    public Result get(@RequestParam int userId) {
        QueryWrapper<Address> wrapper=new QueryWrapper();
        wrapper.eq("user_id",userId);
        List<Address> addresses = addressMapper.selectList(wrapper);
        if(addresses.isEmpty())
            return Result.fail("快去添加地址吧！");
        PageBean<Address> a=new PageBean<>(addresses.size(),addresses);
        return Result.success(a);
    }

    @PutMapping("/upd")
    public Result upd(@RequestBody Address address) {
        int i = addressMapper.updateById(address);
        if (i > 0)
            return Result.success();
        return Result.fail();
    }

    @DeleteMapping("del")
    public Result del(@RequestParam int id) {
        int i = addressMapper.deleteById(id);
        if(i>0)
            return Result.success("删除成功");
        return Result.fail("删除失败");
    }
}
