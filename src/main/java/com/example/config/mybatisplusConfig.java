package com.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mybatisplusConfig {

    //分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor m=new MybatisPlusInterceptor();
        PaginationInnerInterceptor p=new PaginationInnerInterceptor();
        p.setDbType(DbType.MYSQL);
        p.setOverflow(true);//溢出的数据重头开始
        m.addInnerInterceptor(p);
        return m;
    }
}
