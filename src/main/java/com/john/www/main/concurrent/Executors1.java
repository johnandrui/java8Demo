package com.john.www.main.concurrent;

import org.testng.annotations.Test;

import java.util.concurrent.*;

public class Executors1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
      test1(6);

    }

    @Test
    public static void test1(long seconds){
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(seconds);
                String name=Thread.currentThread().getName();
                System.out.println("task finished:"+name);
            } catch (InterruptedException e) {
                System.err.println("task interrupted");
                e.printStackTrace();
            }
        });
        stop(executorService);
    }
    static void stop(ExecutorService executorService){
        try {
            System.out.println("attempt to shutdown executor");
            executorService.shutdown();
            executorService.awaitTermination(5,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("temination interrupted");
            e.printStackTrace();
            System.out.println("shutdown finished");
        }finally {
            if(!executorService.isTerminated()){
                System.err.println("Killing non-finished tasks");
            }
            executorService.shutdown();
        }
    }



}
