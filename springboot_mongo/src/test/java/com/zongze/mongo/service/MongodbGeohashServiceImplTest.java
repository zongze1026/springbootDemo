package com.zongze.mongo.service;

import com.zongze.mongo.domain.GeoUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Date 2021/1/22 14:29
 * @Created by xiezz
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbGeohashServiceImplTest {

    @Autowired
    private MongodbGeohashService mongodbGeohashService;

    @Test
    public void addGeoUser() {
        String[] name = {"张三", "李四", "王五"};
        for (int i = 0; i < 500000; i++) {
            int n = i % 3;
            BigDecimal lng = new BigDecimal(Math.random() * (130 - 100) + 100).setScale(10, BigDecimal.ROUND_HALF_UP);
            BigDecimal lat = new BigDecimal(Math.random() * 20 + 20).setScale(10, BigDecimal.ROUND_HALF_UP);
            GeoJsonPoint geoJson = new GeoJsonPoint(lng.doubleValue(), lat.doubleValue());
            GeoUser user = new GeoUser(name[n], i % 99, geoJson);
            mongodbGeohashService.addGeoUser(user);
        }
    }

    @Test
    public void find() {
        //x=120.671264, y=30.854961
        List<GeoUser> geoUsers = mongodbGeohashService.fixedDistance(120.658232, 30.875829, 1000, 5000);
        System.out.println(geoUsers.size());
        for (GeoUser geoUser : geoUsers) {
            System.out.println(geoUser);
        }
    }


    @Test
    public void findFixedDistance() {
        //x=120.671264, y=30.854961
        mongodbGeohashService.findFixedDistance(120.658232, 30.875829, 1, 10, false);
    }


}