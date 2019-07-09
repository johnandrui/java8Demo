package com.john.www.main.timetest;

import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:  操作时间
 * @Date 2018/5/11  18:44
 * @Modified By:
 */
public class TestOperateTime {
    /**
     *  TemporalAdjuster:时间校正器
     */
    @Test
    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldt1 =ldt.withDayOfMonth(10);
        System.out.println(ldt1);
        LocalDateTime ldt3 =ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        System.out.println("*********************************");

        //自定义下一个工作日
        LocalDateTime ldt5 =  ldt.with(l -> {
            LocalDateTime ldt4 =(LocalDateTime) l;
            DayOfWeek dow = ldt4.getDayOfWeek();
            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt4.plusDays(3);
            }else if(dow.equals(DayOfWeek.SATURDAY)){
                return ldt4.plusDays(2);
            }else {
                return ldt4.plusDays(1);
            }

        });
        System.out.println("nextWorkDate:"+ldt5);

    }

    /**
     *  DateTimeFormatter:格式化时间/日期
     */
    @Test
    public void test2(){
        //时间格式API
       DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        String strDate = dtf.format(ldt);
        System.out.println(strDate);
         //自定义时间格式
        DateTimeFormatter dtf1 =DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2= dtf1.format(ldt);
        System.out.println(strDate2);
        //字符串转日期
        LocalDateTime  ldt2 = ldt.parse(strDate2,dtf1);
        System.out.println(ldt2);
  }
    /**
     *  DateTimeFormatter:时区处理
     *  ZonedDate、ZonedTime、ZonedDateTime
     */
    @Test
    public void test3(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }
    @Test
    public void test4(){
       LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
       System.out.println(ldt);

       LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
       ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Europe/Tallinn"));
       System.out.println(zdt);
    }

}
