package com.john.www.main.completableFuture;

import org.testng.annotations.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuture3 {
    @Test
    public void test1(){
        try {
            String result= CompletableFuture.supplyAsync(()->123)
                    .thenCompose(t -> CompletableFuture.supplyAsync(()-> t+"ddd")
            ).get();
            System.out.println("result="+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    /**
     * 组合
     */
    @Test
    public void test2(){
        try {
            String result= CompletableFuture.supplyAsync(()->123)
                    .thenCombine(CompletableFuture.supplyAsync(()-> "ddd"),
                            (a,b)->{
                                System.out.println("a="+a);
                                System.out.println("b="+b);
                                return a+b;
                            }
                    ).get();
            System.out.println("result="+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    /**
     *either
     */
    @Test
    public void test3(){

    }
}
