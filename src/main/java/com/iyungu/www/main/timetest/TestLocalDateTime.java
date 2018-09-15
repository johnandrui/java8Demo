package com.iyungu.www.main.timetest;


import org.testng.annotations.Test;

import java.time.*;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:    本地时间和日期
 * @Date 2018/5/11  17:54
 * @Modified By:
 */
public class TestLocalDateTime {
    /**
     *  1. localDate  localTime  localDateTime
     */
    @Test
    public void test1(){
        //当前日期
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        //指定日期
        LocalDateTime ldt1 =LocalDateTime.of(2018,10,10,8,02,00);
        System.out.println(ldt1);
        //增加年
        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);
        //减少月份
        LocalDateTime ldt4 = ldt.minusMonths(2);
        System.out.println(ldt4);

        //获取指定的时间数据
        System.out.println("year:"+ldt.getYear());
        System.out.println("month:"+ldt.getMonth());
        System.out.println("monthValue:"+ldt.getMonthValue());
        System.out.println("week:"+ldt.getDayOfWeek());
        System.out.println("hour:"+ldt.getHour());
        System.out.println("minute:"+ldt.getMinute());
        System.out.println("second:"+ldt.getSecond());
        System.out.println("dayOfWeek:"+ldt.getDayOfWeek());
        System.out.println("dayOfMonth:"+ldt.getDayOfMonth());
        System.out.println("dayOfYear:"+ldt.getDayOfYear());

    }

    /**
     *  2. Instant:时间戳(以Unix(系统) 元年：1970年1月1日00:00:00之间的毫秒数)
     */
    @Test
    public void test2(){
        Instant ins1 = Instant.now();//默认获取 UTC时区
        System.out.println(ins1);

        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        System.out.println(ins1.toEpochMilli());  //毫秒
        Instant ins2 = Instant.ofEpochSecond(60); //加60秒
        System.out.println(ins2);
    }
    /**
     *  3.  Duration:计算两个"时间"的间隔
     *      Period :计算两个“日期”的间隔
     *
     */
    @Test
    public void test3(){
          Instant ins1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant ins2 = Instant.now();
        Duration duration = Duration.between(ins1,ins2);
        System.out.println(duration.toMillis());
        System.out.println("**********************************");

        LocalTime lt1 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime lt2 = LocalTime.now();
        Duration duration1 = Duration.between(lt1,lt2);
        System.out.println(duration1.toMillis());

        //日期间隔
        LocalDate ld1 = LocalDate.of(2015,1,1);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1,ld2);
        System.out.println("period:"+period);
        System.out.println("period(y):"+period.getYears());
        System.out.println("period(m):"+period.getMonths());
        System.out.println("period(d):"+period.getDays());
    }
}
