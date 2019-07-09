package com.john.www.main.concurrent;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Executors3 {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
        test3();
    }
    public static void test3(){
        ScheduledExecutorService executor= Executors.newScheduledThreadPool(1);
        Runnable task=()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling:"+System.nanoTime());
            } catch (InterruptedException e) {
                System.err.println("task interrupted");
//                e.printStackTrace();
            }
        };
        executor.scheduleWithFixedDelay(task,0,1,TimeUnit.SECONDS);
    }
    //定时执行
    public static void test2(){
        ScheduledExecutorService executor= Executors.newScheduledThreadPool(1);
        Runnable task=()->System.out.println("Scheduling:"+System.nanoTime());
        int initialDelay=0;
        int period=5;
        executor.scheduleAtFixedRate(task,initialDelay,period,TimeUnit.SECONDS);
    }
    public static void test1() throws InterruptedException {
        ScheduledExecutorService executor= Executors.newScheduledThreadPool(1);
        Runnable task=()->System.out.println("Scheduling:"+System.nanoTime());
        int delay =3;
        ScheduledFuture<?> future=executor.schedule(task,delay, TimeUnit.SECONDS);
        TimeUnit.MILLISECONDS.sleep(1337);
        long remainingDelay=future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay:%sms\n",remainingDelay);
    }
}
