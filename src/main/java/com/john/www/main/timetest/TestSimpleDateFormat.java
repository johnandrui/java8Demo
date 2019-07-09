package com.john.www.main.timetest;

import com.john.www.threadlock.DateFormatThreadLocal;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

    @Test
    public void testFormate(){
        DateTimeFormatter formatter=DateTimeFormatter.BASIC_ISO_DATE;
        String formatterDate= formatter.format(LocalDate.now());
        String formattedZoneDate=formatter.format(ZonedDateTime.now());
        System.out.println("formatterDate="+formatterDate+"\nformattedZoneDate="+formattedZoneDate);

        LocalDateTime dateTime=LocalDateTime.now();
        String strDate1=dateTime.format(DateTimeFormatter.BASIC_ISO_DATE);//20180102
        String strDate2=dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);//2018-01-02
        String strDate3=dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);//当前时间
        String strDate4=dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));//2018-01-
        //今天是：2018年 三月03日 星期六
        String strDates=dateTime.format(DateTimeFormatter.ofPattern("今天是：YYYY年 MMM dd日 E", Locale.CHINESE));

        System.out.println("strDate1="+strDate1+"\nstrDate2="+strDate2+"\nstrDate3="+strDate3+"\nstrDate4="+strDate4+"" +
                "\nstrDates="+strDates);

        //将一个字符串解析成一个日期对象
        String strDate6="2018-03-03";
        String strDate7="2017-03-03 15:30:23";
        LocalDate date=LocalDate.parse(strDate6,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime dateTime1=LocalDateTime.parse(strDate7,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("date="+date+"\ndateTime1="+dateTime1);
    }
}
