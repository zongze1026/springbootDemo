package com.zongze.mongo.geohash;

public class App {

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        System.out.println(new GeoHashHelper().around(44.9999, 116.3967));
//        System.out.println(new GeoHashHelper().around(45.0001, 116.3967));
//        System.out.println(DistanceHepler.distance(44.9999, 116.3967, 45.0001, 116.3967));
//        System.out.println("waste time: " + (System.currentTimeMillis() - start));
        // long start = System.currentTimeMillis();
        // System.out.println(new GeoHashHelper().encode(44.9999, 116.3967));
        // System.out.println(new GeoHashHelper().encode(45.0001, 116.3967));
        // System.out.println("waste time: " + (System.currentTimeMillis() - start));
        //30.316284,120.176482  30.316488,120.172555
        System.out.println(DistanceHepler.distance(30.314558,120.172638 , 30.316497,120.172491));
    }

}
