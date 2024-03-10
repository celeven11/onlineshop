package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.GoodsMapper;
import com.example.pojo.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OnlineshopApplicationTests {

    @Autowired
    private ResourceLoader resourceLoader;


    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void test1() {
        String s="小米";
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("goods_name", s).or()
                .like("description",s);
        List<Goods> goods = goodsMapper.selectList(wrapper);
        System.out.println(goods);
     }
    @Test
    void contextLoads() throws IOException {
        List<String> pics= new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:/static/imgs/" + "1"+"/1");
        File file = resource.getFile();
        File[] files = file.listFiles();
        for (File file1 : files) {
            pics.add("imgs/1/1/"+file1.getName());
        }
        for (String pic : pics) {
            System.out.println(pic);
        }
    }




    //使用pagehelper分页
//    @Test
//    public void getbypage(){
//        PageHelper.startPage(1, 5);
//        List<Goods> goodsList = goodsMapper.selectList(null);
//        Page<Goods> p=(Page<Goods>) goodsList;
//        PageBean<Goods> pg=new PageBean<>((int)p.getTotal(),p.getResult());
//        List<Goods> result = p.getResult();
//        for (Goods goods : result) {
//            System.out.println(goods);
//        }
//    }

    //使用mybatisplus插件
    @Test
    public void getbypage2(){
        Page<Goods> p=new Page<>(24,5);
//        IPage<Goods> goodsPage = goodsMapper.selectPage(p, null);
        Page<Goods> goodsPage = goodsMapper.selectPage(p, null);
        List<Goods> records = goodsPage.getRecords();
        for (Goods record : records) {
            System.out.println(record);
        }
        System.out.println(goodsPage.getTotal());
        System.out.println(goodsPage.getCurrent());
        System.out.println(goodsPage.getSize());
        System.out.println(goodsPage.getPages());

    }


}
