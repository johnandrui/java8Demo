package com.iyungu.www.main.concurrent;

import javax.jms.IllegalStateException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Executors2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }
    public static void test5() throws ExecutionException, InterruptedException {
        ExecutorService executorService=Executors.newWorkStealingPool();//java8新的创建线程池的方式Forkjoin与计算机内核有关
        List<Callable<String>> callables= Arrays.asList(
                callable("task1",2),
                callable("task2",1),
                callable("task3",3)
        );
        String result =executorService.invokeAny(callables);
        System.out.println(result);
        executorService.shutdown();

    }
    private static  Callable<String> callable(String result,long sleepSeconds){
        return ()->{
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    //批量
    public static void test4() throws InterruptedException {
        ExecutorService executorService=Executors.newWorkStealingPool();
        List<Callable<String>> callables= Arrays.asList(
                ()->"task1",
                ()->"task2",
                ()->"task3"
        );
        executorService.invokeAll(callables)
                .stream()
                .map(future->{
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new java.lang.IllegalStateException(e);
//                        e.printStackTrace();
                    }
                })
                .forEach(System.out::println);
        executorService.shutdown();
    }
    //超时
    public static void test3() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService=Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(Executors2::call);
        future.get(1,TimeUnit.SECONDS);
    }
//中断
    public  static void test2() throws ExecutionException, InterruptedException {
        ExecutorService executorService=Executors.newFixedThreadPool(1);
     /*   Future<Integer> future=executorService.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted",e);
//                e.printStackTrace();
            }
        });*/
        Future<Integer> future = executorService.submit(Executors2::call);
        executorService.shutdownNow();
        future.get();
    }
    public static void test1() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(Executors2::call);
        System.out.println("future done:"+future.isDone());
        Integer result=future.get();
        System.out.println("future done:"+future.isDone());
        System.out.println("result:"+result);
        executor.shutdownNow();
    }

    private static Integer call() throws IllegalStateException {
        try {
            TimeUnit.SECONDS.sleep(2);
            return 123;
        } catch (InterruptedException e) {
            throw new IllegalStateException("task interrupted", e.toString());
//                e.printStackTrace();
        }
    }
}
