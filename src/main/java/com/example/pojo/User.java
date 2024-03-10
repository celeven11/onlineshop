package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @TableId
    private int id;
    private String username;
    private String nickname;
    private String userpic;
    private String phone;
    //返回是忽略密码
//    @JsonIgnore 使用这个会忽略前端传来的数据
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
