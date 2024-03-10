package com.example.utils;

import com.example.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoodsPic {
    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${server.port}")
    private String port;

    private String http = "http://localhost:";

    public List<Goods> getImgs(List<Goods> goods) {
        for (Goods good : goods) {
            // 获取图片
            getImg(good);
        }
        return goods;
    }

    //获取商品图片
    public Goods getImg(Goods good) {
        List<String> imgs = new ArrayList<>();
        int categoryId = good.getCategoryId();
        int goodsId = good.getGoodsId();
        String url = "imgs/" + categoryId + "/" + goodsId;
        Resource resource = resourceLoader.getResource("classpath:/static/" + url);
        try {
            File[] files = resource.getFile().listFiles();
            for (File file : files) {
                String fileName = file.getName();
                imgs.add(http+port + "/static/" + url + "/" + fileName);
            }
            good.setImgs(imgs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return good;
    }

    //获取用户头像
    public List<String> getUserPic() {
        List<String> imgs = new ArrayList<>();
        String url="/static/imgs/userpic";
        Resource resource = resourceLoader.getResource("classpath:" + url);
        File[] files = new File[0];
        try {
            files = resource.getFile().listFiles();
            for (File file : files) {
                imgs.add(url+"/" + file.getName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imgs;
    }
}
