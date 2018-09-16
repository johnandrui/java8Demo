package com.iyungu.www.main.concurrent;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Synchronized1 {
    static int count;
    public static final Integer NUM_INCREMENTS=10000;

    public static void main(String[] args) {
        testNonSyncIncrement();
    }

    public static void testNonSyncIncrement(){
        count=0;
        ExecutorService executor= Executors.newFixedThreadPool(2);
        IntStream.range(0,NUM_INCREMENTS)
                .forEach(i->executor.submit(Synchronized1::increment));
       com.iyungu.www.main.concurrent.ConcurrentUtils.stop(executor);
        System.out.println("count="+count);

    }
    static synchronized  void incrementSync(){count = count +1;}
    static void increment() {
        count = count + 1;
    }
}
