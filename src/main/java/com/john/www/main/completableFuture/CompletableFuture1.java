package com.john.www.main.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFuture1 {
    public static void main(String[] args) {
        //无返回值的Future,默认使用ForkJoin线程池
     /*  CompletableFuture<Void> hello_future=CompletableFuture.runAsync(()->{
            System.out.println("Hello future");
        });
       CompletableFuture<Integer> integerCompletableFuture=CompletableFuture.supplyAsync(()->123);*/
    CompletableFuture<Integer> integerCompletableFuture= CompletableFuture.supplyAsync(()->{
         System.out.println("开始执行运算！");
         try {
             TimeUnit.SECONDS.sleep(3);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         int a=1/0;
         System.out.println("结束执行运算！");
         return 233;
     });
        try {
            Integer result=integerCompletableFuture.whenComplete((a,b)->{
                System.out.println("Result:"+a);
                System.out.println("Exception:"+b);
            }).exceptionally(e ->{
                System.out.println(e.getMessage());
                return 888;
            }).get();
            System.out.println("final result:"+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
