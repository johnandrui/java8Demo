package com.john.www.main.StreamAPI;

import com.alibaba.fastjson.JSONObject;
import com.john.www.entity.Employee;
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
public class TestStreamAPI3 {

       /**
        *   终止（端）操作
        *   查找与匹配
        *   allMatch --- 检查是否匹配所有元素
        *   anyMatch --- 检查是否至少匹配一个元素
        *   noneMatch --- 检查是否没有匹配所有元素
        *   findFirst --- 返回当前流中第一个元素
        *   findAny --- 返回当前流中的任意元素
        *   count --- 返回流中元素的总个数
        *   max --- 返回流中最大值
        *   min --- 返回流中最小值
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
           boolean b1 =employees.stream()
                   .allMatch(e->e.getStatus().equals(Employee.Status.BUSY));
           System.out.println(b1);

           boolean b2 =employees.stream()
                   .anyMatch(e ->e.getStatus().equals(Employee.Status.BUSY));
           System.out.println(b2);

           boolean b3 = employees.stream()
                   .noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
           System.out.println(b3);
                                             //串行
           Optional<Employee> op = employees.stream()
                   .sorted((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()))
                   .findFirst();
           System.out.println(op.get());
                                               //并行流，获取的对象不一定
            Optional<Employee> op1 = employees.parallelStream()
                    .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                    .findAny();
            System.out.println(op1);
       }
      @Test
    public void test2(){
       Long count = employees.stream()
       .count();
       System.out.println(count);
       Optional<Employee> op =employees.parallelStream()
               .max((e1,e2) -> Integer.compare(e1.getAge(),e2.getAge()));
       System.out.println(op);

         Optional<Integer> op1 = employees.stream()
                 .map(Employee::getAge)
                 .min(Integer::compare);
         System.out.println(op1);
      }
    /**
     *   终止（端）操作
     *   归约
     *   reduce(T identity,BinaryOperator) /reduce(BinaryOperator) --可以将流中元素反复
     *   结合起来，得到一个值
     */
    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream()
                .reduce(0,(x,y) -> x+y);
        System.out.println(sum);

        Optional<Double> op =employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());

    }
    /**
     *   终止（端）操作
     *   收集
     *    collect --- 将流转换为其他形式，接收一个Collector接口的实现，
     *                用于给Stream中元素做汇总的方法
     */
    @Test
    public void test4(){
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(list);
        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        HashSet<String> hashSet =employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

    }
    @Test
    public void test5(){
        //总数
       Long count = employees.stream()
                      .collect(Collectors.counting());
       //平均值
        double average =employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        //总和
        employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        //最大
        Optional<Employee> employee =employees.stream()
                .collect(Collectors.maxBy((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary())));
        //最小
        Optional<Double> op = employees.stream()
                                 .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
    }

    @Test
    public void test6(){
        //分组
        Map<Employee.Status,List<Employee>> map =employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
        //多级分组
        Map<Employee.Status,Map<String,List<Employee>>> map1 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy(e ->{
                    if(e.getAge()<=35){
                        return "青年";
                    }else if(e.getAge() <50){
                        return "中年";
                    }else {
                        return "老年";
                    }
                })));
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map1);
        System.out.println(jsonObject);

    }
    /**
     * 分区
     */
    @Test
    public void test7(){
        Map<Boolean,List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
        System.out.println(jsonObject);
        //运算分区
        DoubleSummaryStatistics dss =employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.toString());
        //连接
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(str);
    }


}
