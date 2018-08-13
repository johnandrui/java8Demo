package com.iyungu.www.main.StreamAPI;

import com.alibaba.fastjson.JSONObject;
import com.iyungu.www.entity.Employee;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:  强大的streamAPI
 * @Date 2018/5/10  15:53
 * @Modified By:
 */
public class TestStreamAPI4 {

       /**
        * 练习：
        *  1.给定一个数字列表，如何返回一个由每个数的平方组成的列表？
        *  给定【1,2,3,4,5】，返回【1,4,9,25】
        */
       List<Employee> employees = Arrays.asList(
               new Employee("张三",18,4000.12, Employee.Status.FREE),
               new Employee("李思",38,5000.22, Employee.Status.BUSY),
               new Employee("肇东",58,6000.11, Employee.Status.BUSY),
               new Employee("虎门",18,7000.88, Employee.Status.VOCATION),
               new Employee("刘工",46,1000.88, Employee.Status.FREE),
               new Employee("刘工",48,9000.88, Employee.Status.FREE),
               new Employee("童敏",8,4000.00, Employee.Status.VOCATION)
       );
       @Test
    public void test1(){
           Integer[] nums = new Integer[]{1,2,3,4,5};
           Arrays.stream(nums)
                   .map(x -> x * x)
                   .forEach(System.out::println);

       }
    /**
     * 练习：
     *  2.怎样用map和reduce方法数一数流中有多少Employee?
     */
    @Test
    public void test2(){
        Optional<Integer> count = employees.stream()
                .map(e ->1)
                .reduce(Integer::sum);
    }

    /**
     * 练习：
     *  1.找出2011年发生的所有交易，并按交易额排序（从低到高）
     *  2.交易员都在哪些不同的城市工作过？
     *  3.查找所有来自剑桥的交易员，并按姓名排序
     *  4.返回所有交易员名字的字符串，按字母顺序排序
     *  5.有没有交易员是在米兰工作的？
     *  6.打印生活在剑桥的交易员的所有交易额
     *  7.所有交易中，最高的交易额是多少？
     *  8.找到交易额最小的交易
     */
      @Test
    public void test3(){
     //1.找出2011年发生的所有交易，并按交易额排序（从低到高）

      }



}
