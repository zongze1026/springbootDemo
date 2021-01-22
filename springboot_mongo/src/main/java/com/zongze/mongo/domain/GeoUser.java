package com.zongze.mongo.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

/**
 * @Date 2021/1/22 14:20
 * @Created by xiezz
 */
public class GeoUser {

    private String name;

    private Integer age;

    private GeoJsonPoint location;

    public GeoUser(String name, Integer age, GeoJsonPoint location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "GeoUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", location=" + location +
                '}';
    }
}
