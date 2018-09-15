package com.iyungu.www.main.timetest;

import javax.xml.crypto.Data;
import java.time.*;
import java.util.Date;

public class ConvertUtils {
    /**
     * LocalDate -> Date
     */
    public static Date toDate(LocalDate localDate){
        ZoneId zone=ZoneId.systemDefault();
        Instant instant=localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }
    /**
     * LocalDateTime -> Date
     */
    public static Date toDate(LocalDateTime localDateTime){
        ZoneId zone=ZoneId.systemDefault();
        Instant instant=localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }
    /**
     * LocalTime -> Date
     */
    public static Date toDate(LocalTime localTime){
        LocalDate localDate=LocalDate.now();
        LocalDateTime localDateTime=LocalDateTime.of(localDate,localTime);
        ZoneId zone=ZoneId.systemDefault();
        Instant instant=localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }
    /**
     * Date -> LocalDate
     */
    public static LocalDate toLocalDate(Date date){
        Instant instant=date.toInstant();
        ZoneId zone=ZoneId.systemDefault();
        LocalDateTime localDateTime=LocalDateTime.ofInstant(instant,zone);
        return localDateTime.toLocalDate();
    }
    /**
     * Date -> LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date){
       Instant instant=date.toInstant();
       ZoneId zone= ZoneId.systemDefault();
       return LocalDateTime.ofInstant(instant,zone);
    }
    /**
     * Date -> LocalTime
     */
    public static LocalTime toLocalTime(Date date){
        Instant instant=date.toInstant();
        ZoneId zone=ZoneId.systemDefault();
        LocalDateTime localDateTime=LocalDateTime.ofInstant(instant,zone);
        return localDateTime.toLocalTime();
    }
 }
