package com.john.www.main.completableFuture;

import java.util.concurrent.*;

public class Future1 {
    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService= Executors.newWorkStealingPool(2);
        Future<Integer> submit=executorService.submit(()->{
            TimeUnit.SECONDS.sleep(3);
            return 100;
        });
    /*    try {
            Integer result = submit.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    while(!submit.isDone()){
        try {
            TimeUnit.MICROSECONDS.sleep(100);
            Integer result=submit.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
      }
}
