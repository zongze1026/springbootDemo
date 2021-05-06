package com.zongze.spi;

import java.util.List;

/**
 * @Date 2021/4/25 14:42
 * @Created by xiezz
 */
public class SpiTest {


    public static void main(String[] args) {
//        ServiceLoader<Search> load = ServiceLoader.load(Search.class);
//        Iterator<Search> iterator = load.iterator();
//        while (iterator.hasNext()){
//            Search next = iterator.next();
//            next.searchDoc("美女");
//        }


    }


    public <T extends E & F> T test(T t) {
        return t;
    }


    // 如下funD方法会报错
    public static void funC(List<? extends A> listA) {
        // ...
    }

    public static void funD(List<B> listB) {
        funC(listB); // Unresolved compilation problem: The method doPrint(List<A>) in the type test is not applicable for the arguments (List<B>)
        // ...
    }


}
