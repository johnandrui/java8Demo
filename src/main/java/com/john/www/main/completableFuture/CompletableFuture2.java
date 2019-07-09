package com.john.www.main.completableFuture;

import org.testng.annotations.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class CompletableFuture2 {
    public static void main(String[] args) {

    }
    public static void test1(){
        try {
            String result=CompletableFuture.supplyAsync(()->{
                return 123;
            }).handle((integer,throwable) ->{
                if(null != throwable){
                    System.out.println(throwable.getMessage());
                }
                return String.valueOf(integer);
            }).get();
            System.out.println("result="+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public static void test2(){
        try {
            String result=CompletableFuture.supplyAsync(()->{
                return 123;
            }).thenApply(t ->{
                return String.valueOf(t);
            }).get();
            System.out.println("result="+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Test
    public static void test3(){
        try {
            String result=CompletableFuture.supplyAsync(()->{
                return 123;
            }).thenCompose(t ->{
             return  CompletableFuture.supplyAsync(()->{
                   return t+"ddd";
               });
            }).get();
            System.out.println("result="+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费型
     */
    @Test
    public static void test4(){
        try {
            CompletableFuture.supplyAsync(()->{
                return 898;
            }).thenAccept(t ->{
                System.out.println(t);
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    /**
     * 消费型
     */
    @Test
    public static void test5(){
        try {
            CompletableFuture.supplyAsync(()->{
                return 898;
            }).thenAcceptBoth(CompletableFuture.supplyAsync(()->"7878"),(a,b)->{
                System.out.println("a="+a);
                System.out.println("b="+b);
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Test
    public static void test6(){
        try {
            CompletableFuture.supplyAsync(()->{
                try {
                    System.out.println("开始执行！");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 898;
            }).thenRun(()->{
                System.out.println("结束执行！");
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
