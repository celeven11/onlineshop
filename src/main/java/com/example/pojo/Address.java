package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Address {
    @TableId
    private int id;
    private int userId;
    private String username;
    private String phone;
    private String province;
    private String city;
    private String county;
    private String detail;
}
