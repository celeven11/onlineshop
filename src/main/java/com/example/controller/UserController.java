package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import com.example.pojo.Result;
import com.example.utils.GoodsPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private GoodsPic goodsPic;

    //获取所有头像信息
    @GetMapping("/imgs")
    public Result imgs() {
        List<String> userPic = goodsPic.getUserPic();

        return Result.success(userPic);
    }

    //查找用户
    @GetMapping("/find")
    public Result<User> finduser(@RequestParam String username) {
        User user = userService.finduser(username);
        if (user != null) {
            return Result.success(user);
        }
        return Result.fail();
    }

    //用户登录
    @GetMapping("/login")
    public Result<User> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    //注册用户
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    //修改密码
    @PutMapping("/updatepwd")
    public Result updatepwd(@RequestBody Map<String, String> map) {
        return userService.updatepwd(map);
    }

    //修改用户信息
    @PutMapping("/updateinfo")
    public Result updateinfo(@RequestBody User user) {
        return userService.updateinfo(user);
    }

}
