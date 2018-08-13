package com.iyungu.www.main.timetest;

import com.iyungu.www.threadlock.DateFormatThreadLocal;
import org.testng.annotations.Test;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description: 日期测试
 * @Date 2018/5/11  17:00
 * @Modified By:
 */
public class TestSimpleDateFormat {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
      //  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        //实例：执行
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
               // return sdf.parse("20161218");
                return DateFormatThreadLocal.convert("20161218");
            }
        };

       //多线程：同时时间格式化
        ExecutorService pool = Executors.newFixedThreadPool(10);//长度为10的线程池
        List<Future<Date>>  results = new ArrayList<>();
         for(int i=0;i<10;i++){
           results.add(pool.submit(task));
         }
         for(Future<Date> future : results){
             System.out.println(future.get());
         }
          pool.shutdown();
    }
    @Test
    public void test() throws ExecutionException, InterruptedException {
        //java8的新操作
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

        //实例：执行
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                // return sdf.parse("20161218");
                return LocalDate.parse("20161218",df);
            }
        };

        //多线程：同时时间格式化
        ExecutorService pool = Executors.newFixedThreadPool(10);//长度为10的线程池
        List<Future<LocalDate>>  results = new ArrayList<>();
        for(int i=0;i<10;i++){
            results.add(pool.submit(task));
        }
        for(Future<LocalDate> future : results){
            System.out.println(future.get());
        }
        pool.shutdown();
    }
}
