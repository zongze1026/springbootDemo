package com.zongze.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Date 2021/4/25 14:42
 * @Created by xiezz
 */
public class SpiTest {


    public static void main(String[] args) {
        ServiceLoader<Search> load = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = load.iterator();
        while (iterator.hasNext()){
            Search next = iterator.next();
            next.searchDoc("美女");
        }
    }



}
