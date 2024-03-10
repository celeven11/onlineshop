package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Orders {
    @TableId
    private int orderId;
    private int userId;
    private int goodsId;
    private int  addressId;
    private int num;
    private double price;
    private int state;
    private LocalDateTime orderTime;
    private LocalDateTime endTime;
    @TableField(exist = false)
    private Goods goods;
    @TableField(exist = false)
    private Address address;
}
