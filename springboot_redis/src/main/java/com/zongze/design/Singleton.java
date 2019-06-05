package com.zongze.design;

/**
 * Create By xzz on 2019/1/23
 * 单例设计模式
 */
public class Singleton {

    /**
     *采用private修饰的静态内部类实现;Singleton中没有static修饰的变量所有初始化不会创建任何对象可以节省内存的开销
     */
    private static class SingletonInstance{
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance(){
        /**
         *当外部调用该方法的时候；jvm会初始化内部类，并初始化好singleton对象；所有这里就不需要加同步就可以实现（类似饿汉式）
         */
        return SingletonInstance.singleton;
    }

    private Singleton(){}


    /**
     *100个线程获取到的对象都是同一个
     */
    public static void main(String[] args) {
        for (int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Singleton.getInstance());
                }
            }).start();
        }
    }





}
