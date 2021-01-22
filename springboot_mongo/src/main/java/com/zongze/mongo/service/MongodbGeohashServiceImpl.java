package com.zongze.mongo.service;

import com.alibaba.fastjson.JSON;
import com.sun.media.sound.SoftTuning;
import com.zongze.mongo.domain.GeoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;


/**
 * @Date 2021/1/22 14:24
 * @Created by xiezz
 */
@Service
public class MongodbGeohashServiceImpl implements MongodbGeohashService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static String collectionName = "geohash";

    private static final Logger LOGGER = LoggerFactory.getLogger(MongodbGeohashServiceImpl.class);


    @Override
    public GeoUser addGeoUser(GeoUser geoUser) {
        return mongoTemplate.insert(geoUser, collectionName);
    }


    @Override
    public List<GeoUser> fixedDistance(double x, double y, Integer minDistance, Integer maxDistance) {
        Query query = new Query();
        query.addCriteria(Criteria.where("location")
                .nearSphere(new GeoJsonPoint(x, y))
                .minDistance(minDistance)
                .maxDistance(maxDistance));
        List<GeoUser> geoUsers = mongoTemplate.find(query, GeoUser.class, collectionName);
        return geoUsers;
    }


    @Override
    public void findFixedDistance(double x, double y, Integer minDistance, Integer maxDistance, boolean spherical) {
        NearQuery nearQuery = NearQuery.near(new GeoJsonPoint(x, y),Metrics.KILOMETERS) //指定坐标,返回距离指定距离单位（公里）
                .minDistance(minDistance) //最小距离
                .maxDistance(maxDistance) //最大距离
                .spherical(true)
                .query(Query.query(Criteria.where("name").is("张三"))); //增加筛选条件

        GeoResults<GeoUser> geoResults = mongoTemplate.geoNear(nearQuery, GeoUser.class, collectionName);

        Iterator<GeoResult<GeoUser>> iterator = geoResults.iterator();
        while (iterator.hasNext()){
            GeoResult<GeoUser> geoResult = iterator.next();
            GeoUser content = geoResult.getContent();
            LOGGER.info("获取到的实例对象：{}，距离为：{}",JSON.toJSONString(content),geoResult.getDistance().getValue());
        }
    }



}
