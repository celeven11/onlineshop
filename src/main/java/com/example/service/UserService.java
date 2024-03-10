package com.example.service;

import com.example.pojo.Result;
import com.example.pojo.User;

import java.util.Map;

public interface UserService {
    User finduser(String username);

    Result<User> login(String username,String password);

    Result register(User user);

    Result updatepwd(Map<String,String> map);

    Result updateinfo(User user);

}
