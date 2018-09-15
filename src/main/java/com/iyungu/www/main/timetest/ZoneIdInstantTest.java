package com.iyungu.www.main.timetest;

import org.testng.annotations.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.locks.Lock;

public class ZoneIdInstantTest {
    //时区zoneId
    @Test
    public void test1(){
        //获取系统默认时区
        ZoneId defaultZoneId=ZoneId.systemDefault();
        ZoneId shanghaiZoneId=ZoneId.of("Asia/Shanghai");

        //TimeZone 转换为 ZoneId
        ZoneId oldToNewZoneId= TimeZone.getDefault().toZoneId();
        System.out.println("defaultZoneId:"+defaultZoneId+":\nshanghaiZoneId:"+shanghaiZoneId+"\noldToNewZoneId:"+oldToNewZoneId);

    }
    //Instant
    @Test
    public void testInstant(){
        //创建Instant实例
        Instant now=Instant.now();
        //访问Instant的时间
        long seconds=now.getEpochSecond();
        int nanos=now.getNano();//纳秒
        System.out.println("seconds="+seconds+"\nnanos="+nanos);

        //3秒后
        Instant later=now.plusSeconds(3);
        //3秒前
        Instant earlier= now.minusSeconds(3);
        System.out.println("current="+now+"\nlater="+later+"\nearlier="+earlier);
    }
    //Clock
    @Test
    public void testClock(){
        Clock clock=Clock.systemDefaultZone();
        long mills=clock.millis();
        Instant instant= clock.instant();
        Date legacyDate=Date.from(instant);
        System.out.println("mills="+mills+"\nlegacyDate="+legacyDate);
    }

}
