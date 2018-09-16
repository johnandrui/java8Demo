package com.iyungu.www.main.concurrent;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Lock1 {
    private static int count=0;
   static  ReentrantLock lock = new ReentrantLock();
    static void increment(){
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        testLock();
    }

    private static void testLock() {
        count=0;
        ExecutorService executor= Executors.newFixedThreadPool(2);
        IntStream.range(0,10000)
                .forEach(i -> executor.submit(Lock1::increment));
        ConcurrentUtils.stop(executor);
        System.out.println(count);
    }
}
