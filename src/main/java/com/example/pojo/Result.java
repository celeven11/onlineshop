package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
    public static <E> Result<E> success(E data){
        return new Result<>(200,"操作成功",data);
    }
    public static Result success(){
        return new Result<>(200,"操作成功",null);
    }
    public static Result success(String msg){
        return new Result<>(200,msg,null);
    }
    public static Result fail(){
                return new Result<>(400,"操作失败",null);
    }
    public static Result fail(String msg){
                return new Result<>(400,msg,null);
    }

}
