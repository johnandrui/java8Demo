package com.iyungu.www.main.concurrent;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Thread1 {
    @Test
    public void test1() {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };
        runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Done!");
    }
    @Test
    public static void test2(){
        Runnable runnable=()->{
            try {
                System.out.println("Foo"+ Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println("Bar"+ Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }
    @Test
    public static void test3(){
        Runnable runnable=()->{
            try {
                System.out.println("Foo"+Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);//1秒
                System.out.println("Bar"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }

    public static void main(String[] args) {
//        test2();
        test3();
    }
}
