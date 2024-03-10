package com.example.pojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("shoppingcart")
public class ShoppingCart {
    @TableId
    private int id;
    private int userId;
    private int goodsId;
    private int num;
    private Integer selected;
    @TableField(exist = false)
    private Goods goods;
}

