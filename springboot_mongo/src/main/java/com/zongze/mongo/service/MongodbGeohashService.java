package com.zongze.mongo.service;
import com.zongze.mongo.domain.GeoUser;
import java.util.List;

/**
 * @Date 2021/1/22 14:22
 * @Created by xiezz
 */
public interface MongodbGeohashService {


    /**
     * 添加带经纬度的用户
     *
     * @param geoUser
     * @return void
     */
    GeoUser addGeoUser(GeoUser geoUser);


    /**
     * 指定经纬度查找附近指定距离的人
     *
     * @param x           经度
     * @param y           纬度
     * @param minDistance 最小距离
     * @param maxDistance 最大距离
     * @return com.zongze.mongo.domain.GeoUser
     */
    List<GeoUser> fixedDistance(double x, double y, Integer minDistance, Integer maxDistance);




    /**
     * 指定经纬度查找附近指定距离的人
     *
     * @param x           经度
     * @param y           纬度
     * @param minDistance 最小距离
     * @param maxDistance 最大距离
     * @param spherical 该参数决定距离的计算方式是以球形计算还是以平面计算（球形：true 平面：false）
     * @return com.zongze.mongo.domain.GeoUser
     */
    void findFixedDistance(double x, double y, Integer minDistance, Integer maxDistance,boolean spherical);



}
