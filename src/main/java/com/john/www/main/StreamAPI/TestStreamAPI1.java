package com.john.www.main.StreamAPI;

import com.john.www.entity.Employee;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:  强大的streamAPI
 * @Date 2018/5/10  15:53
 * @Modified By:
 */
public class TestStreamAPI1 {

       /**
        * 一、stream的三个操作步骤：
        *   1.创建Stream
        *   2.中间操作
        *   3.终止（端）操作
        */
       @Test
     public void test1(){
           /**
            *  创建Stream
            *  1.通过Collection系列集合提供的stream（）或paralleStream()并行流
            */
           List<String> list = new ArrayList<>();
           Stream<String> stream1 = list.stream();
           /**
            *  创建Stream
            *  2.通过Arrays中的静态方法stream（）获取数组流
            */
           Employee[] employees = new Employee[10];
           Stream<Employee> stream2 = Arrays.stream(employees);
           /**
            *  创建Stream
            *  3.通过Stream类中的静态方法of()
            */
           Stream<String> stream3 = Stream.of("aa","bb");
           /**
            *  创建Stream
            *  4.创建无限流
            *   (1)迭代
            *   (2)生成
            */
           //(1)迭代
           Stream<Integer> stream4 = Stream.iterate(0,x->x+2);
           stream4.limit(10).forEach(System.out::println);
           //(2)生成
           Stream.generate(()->Math.random())
                 .limit(5)
                 .forEach(System.out::println);
       }

}
