package com.john.www.main.completableFuture;

import org.testng.annotations.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFuture4 {
    /**
     * 只有一个结束执行，则返回结果
     */
    @Test
    public void test1(){

        try {
            m1().acceptEither(m2(),t->{
                System.out.println("t="+t);
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    /**
     * 所有结束执行，则返回结果
     */
    @Test
    public void test2() {
        long start=System.currentTimeMillis();
        try {
            CompletableFuture.allOf(m1(), m2())
            .thenRun(()->{
                System.out.println(System.currentTimeMillis()-start);
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
    private CompletableFuture m1() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2323;
        });
    }

    private CompletableFuture m2() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "2 second result="+2323;
        });
    }
}
