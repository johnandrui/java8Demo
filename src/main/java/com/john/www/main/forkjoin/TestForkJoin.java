package com.john.www.main.forkjoin;

import com.john.www.forkjoin.ForkJoinCalculate;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description: 测试ForkJoin
 * @Date 2018/5/11  14:16
 * @Modified By:
 */
public class TestForkJoin {
     /**
      * ForkJoin框架
      */
    @Test
    public void test(){
      //  long start = System.currentTimeMillis();
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,500000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();                                //毫秒
        System.out.println("ForkJoin框架,耗费时间："+Duration.between(start,end).toMillis());
    }
    /**
     * 普通for循环
     */
    @Test
    public void test1(){
        Instant start = Instant.now();
        long sum = 0L;
        for(long i = 0;i<=500000000000L;i++){
               sum +=i;
        }
        System.out.println(sum);
        Instant end = Instant.now();                                //毫秒
        System.out.println("普通for循环,耗费时间："+Duration.between(start,end).toMillis());
    }
    /**
     * java8 并行流
     */
    @Test
    public void test2(){
        Instant start = Instant.now();
        LongStream.rangeClosed(0,100000000000L)
                .parallel()
                .reduce(0,Long::sum);
        Instant end = Instant.now();                                //毫秒
        System.out.println("java8 并行流,耗费时间："+Duration.between(start,end).toMillis());
    }
}
