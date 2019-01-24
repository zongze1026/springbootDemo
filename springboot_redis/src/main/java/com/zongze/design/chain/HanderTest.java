package com.zongze.design.chain;

/**
 * Create By xzz on 2019/1/24
 */
public class HanderTest {


    public static void main(String[] args) {
        Hander father = new FatherHander();
        Hander mother = new MotherHander();
        Hander brother = new BrotherHander();

        brother.setHander(mother);
        mother.setHander(father);

        String result = brother.doHander(500.00);
        System.out.println(result);

    }



}
