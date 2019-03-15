package com.zongze.test;

import com.zongze.util.DateUtil;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create By xzz on 2019/3/5
 */
public class Test12 extends Abstranct<Double> {


    protected Test12(Double aDouble) {
        super(aDouble);
        init();
    }

    private void init() {
        System.out.println("对象初始化");
    }

    public static Test12 get(){
        return TestHolder.test;
    }

    private static final class TestHolder{
      private static final Test12 test = new Test12(20.00);
    }


    public static void main(String[] args) {

//        timer();
//
//        String date = "2019-01-12";
//        System.out.println(DateUtil.format(DateUtil.parse(date,DateUtil.DATE),DateUtil.DATE_TIME));

        String password = " 124 535 zxs ";
        System.out.println(password.trim().replaceAll(" ",""));




    }

    private static void timer() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("======线程执行中========");



                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("任务执行中");
                        timer.cancel();
                    }
                },new Date());



            }
        }).start();



//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("=====主线程运行结束========");
    }


}
