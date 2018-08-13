package com.iyungu.www.threadlock;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:时间锁
 * @Date 2018/5/11  17:15
 * @Modified By:
 */
public class DateFormatThreadLocal {

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    public static Date convert(String source) throws ParseException {
       return df.get().parse(source);
    }

}
