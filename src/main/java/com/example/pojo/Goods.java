package com.example.pojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Goods {
    @TableId
    private int goodsId;
    private String goodsName;
    private int categoryId;
    private String description;
    private double prePrice;
    private double sellPrice;
    private int num;
    @TableField(exist = false)
    private List<String> imgs;
}

