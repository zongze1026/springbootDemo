package com.zongze.guava;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Date 2020/10/23 10:31
 * @Created by xzz
 */
public class BloomFilterTest {


    public static void main(String[] args) {
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(),500 );
        for (int i=0;i<100;i++){
            filter.put(i);
        }

        int count = 0;
        for (int i=0;i<900000;i++){
            boolean b = filter.mightContain(i);
            if(b){
                count++;
            }
        }

        System.out.println(count);


    }



}
