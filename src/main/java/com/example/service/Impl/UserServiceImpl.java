package com.example.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mapper.UserMapper;
import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User finduser(String username) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public Result<User> login(String username,String password) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("password",password);
        User user1 = userMapper.selectOne(wrapper);
        if(user1 != null){
            return Result.success();
        }
        return Result.fail("用户名或者密码错误");
    }



    @Override
    public Result register(User user) {
        if(user.getUsername()==null||user.getUsername().equals("")){
             return Result.fail("用户名已存在");
        }
        User finduser = finduser(user.getUsername());
        if(finduser!=null){
            return Result.fail("用户名已存在");
        }
        int insert = userMapper.insert(user);
        return Result.success("注册成功");
    }

    @Override
    public Result updatepwd(Map<String,String> map) {
        String id= map.get("id");
        String oldpwd=map.get("oldpwd");
        String newpwd=map.get("newpwd");
        User user = userMapper.selectById(id);
        if(user==null){
            return Result.fail("用户不存在");
        }
        if(!user.getPassword().equals(oldpwd)){
            return Result.fail("原密码错误");
        }
        user.setPassword(newpwd);
        int update = userMapper.updateById(user);
        return Result.success("修改成功");
    }

    @Override
    public Result updateinfo(User user) {
        int update = userMapper.updateById(user);
        if(update>0)
            return Result.success("修改成功");
        return Result.fail();
    }


}
