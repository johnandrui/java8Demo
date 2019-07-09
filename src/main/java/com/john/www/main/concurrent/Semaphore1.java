package com.john.www.main.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Semaphore1 {
    static  Semaphore semaphore = new Semaphore(5);
    static int count=0;
    public static void main(String[] args) {
 testIncrement();
    }
    public static void testIncrement(){
        ExecutorService executor=Executors.newFixedThreadPool(2);
        IntStream.range(0,10000)
                .forEach(i-> executor.submit(Semaphore1::increment));
        ConcurrentUtils.stop(executor);
        System.out.println("Increment:"+count);
    }

    private static void increment() {
        boolean permit = false;
        try {
            permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
            count++;
        } catch (InterruptedException e) {
            throw new RuntimeException("could not increment");
        } finally {
            if (permit) {
                semaphore.release();
            }
        }
    }
}
