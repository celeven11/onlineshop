package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Likes {
    @TableId
    private int likesId;
    private int userId;
    private int goodsId;
}

