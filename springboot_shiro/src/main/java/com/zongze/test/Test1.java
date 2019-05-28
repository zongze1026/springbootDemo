package com.zongze.test;

/**
 * Create By xzz on 2019/3/5
 */
public class Test1 extends Abstranct<Integer> {

    protected Test1(Integer integer) {
        super(integer);
    }

    public Integer get(){
        return this.getObject();
    }


    public static void main(String[] args) {
        String name = "谢";
        byte[] bytes = name.getBytes();
        for (int i=0;i<bytes.length;i++){
            System.out.println(bytes[i]);
        }
    }




}
