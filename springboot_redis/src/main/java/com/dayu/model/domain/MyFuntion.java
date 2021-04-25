package com.dayu.model.domain;

import com.google.common.base.Predicates;
import org.aspectj.weaver.ast.And;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Date 2021/4/9 11:36
 * @Created by xiezz
 */
@FunctionalInterface
public interface MyFuntion<T, V, F> {

    F run(T t, V v);


    public static Integer get(){
        return Integer.valueOf(20);
    }


    public static void main(String[] args) {


        Add add = new Add(10, 20);
        Add add2 = new Add(11, 21);
        Add add3 = new Add(10, 22);
        List<Add> list = Arrays.asList(add, add2, add3);

        Supplier<List<Integer>> s = (Supplier<List<Integer>>) ArrayList::new;
        List<Integer> sorts = list.stream().map(t -> t.getSort()).collect(Collectors.toCollection(s));







//        Map<Boolean, List<Add>> collect = list.stream().collect(Collectors.partitioningBy(t0 -> t0.getSort() <= 10));
//        System.out.println(collect);

//        Integer integer = list.stream().map(Add::getAge).reduce().get();
//        Integer integer = list.stream().map(Add::getAge).reduce((a, b) -> Integer.sum(a, b)).get();
//        Integer integer2 = list.stream().map(Add::getAge).reduce(0, (a, b) -> a += b);
//        System.out.println(integer + integer2);
//        list.stream().collect(Collectors.groupingBy(Add::getAge));
//        list.stream().map(Add::getAge);

//        Map<Boolean, List<Integer>> collectParti = Stream.of(1, 2, 3, 4)
//                .collect(Collectors.partitioningBy(it -> it % 2 == 0));
//        System.out.println("collectParti : " + collectParti);

//        list.stream().reduce()

//        list.stream().peek(t -> t.setAge(t.getAge() + 1)).peek(t -> t.setAge(t.getAge() + 1)).forEach(t2 -> System.out.println(t2.getAge()));

//        list.stream().skip(100).forEach(System.out::println);

//        list.stream().forEach(Add::comsumer1);
//
//        list.stream().filter(Add::test);
//
//        list.stream().filter(a->a.getSort().intValue() == 0);
//
//
//        //int compare(T o1, T o2);
//        list.sort(Comparator.comparing(Add::getSort));
//
//        list.sort(Comparator.comparing(Add::getSort).thenComparing(Add::getAge).thenComparing(Add::getName));
//
//
//        list.stream().filter(Add::test);
//
//        list.stream().max(Comparator.comparing(Add::getAge)).get();
//
//        list.stream().map(Add::getAge).distinct();
//
//        long count = list.stream().count();
//
//        list.stream().reduce((t,t1)->{
//            return new Add(1,1 );
//        }).get();
//
//        List<Integer> numList = Arrays.asList(1,2,3,4,5);
//        int result = numList.stream().reduce((a,b) -> a + b ).get();
//        System.out.println(result);


//        list.stream().peek();


    }


}
