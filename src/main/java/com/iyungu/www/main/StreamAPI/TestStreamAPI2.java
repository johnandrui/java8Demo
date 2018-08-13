package com.iyungu.www.main.StreamAPI;

import com.iyungu.www.entity.Employee;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:  强大的streamAPI
 * @Date 2018/5/10  15:53
 * @Modified By:
 */
public class TestStreamAPI2 {

       /**
        * 一、stream的三个操作步骤：
        *   1.创建Stream
        *   2.中间操作
        *   3.终止（端）操作
        */
       List<Employee> employees = Arrays.asList(
               new Employee("张三",18,4000.12),
               new Employee("李思",38,5000.22),
               new Employee("肇东",58,6000.11),
               new Employee("虎门",18,7000.88),
               new Employee("刘工",48,9000.88),
               new Employee("刘工",48,9000.88),
               new Employee("童敏",8,4000.00)
       );
    /**
     *  中间操作
     *  filter --- 接收Lambda,从流中排除某些元素；
     *  limit ---  截断流，使其元素不超过给定数量
     *  1.通过Collection系列集合提供的stream（）或paralleStream()并行流
     */
    //内部迭代：迭代操作由StreamAPI完成
       @Test
     public void test1(){
          /*  Stream<Employee> stream = employees.stream()
                    .filter(e ->e.getAge()>35);*/
          //中间操作:不会执行任何操作
           Stream<Employee> stream = employees.stream()
                   .filter(e ->{
                       System.out.println("StreamAPI 中间操作");
                       return e.getAge()>35;
                   });
          //终止操作：一次性执行全部内容，即"惰性求值"
           stream.forEach(System.out::println);
       }
       //外部迭代
    @Test
    public void test2(){
         Iterator<Employee> it = employees.iterator();
         while (it.hasNext()){
             System.out.println(it.next());
         }
    }
    /**
     *  中间操作
     *  filter --- 接收Lambda,从流中排除某些元素；
     *  limit ---  截断流，使其元素不超过给定数量；
     *  skip --- 跳过元素，返回一个扔掉了n个元素的流，若流中元素不是n个，则返回一个空流，与limit(n)互补
     *  distinct --- 筛选，通过流所生成元素的hashCode()和equals()去重复元素
     */
     @Test
    public void test3(){
       employees.stream()
       .filter(e ->{
         System.out.println("短路");
         return  e.getSalary()>5000;
       })
       .limit(2)
       .forEach(System.out::println);
     }
    /**
     *  中间操作
     *  筛选与切片
     *  filter --- 接收Lambda,从流中排除某些元素；
     *  limit ---  截断流，使其元素不超过给定数量；
     *  skip --- 跳过元素，返回一个扔掉了n个元素的流，若流中元素不是n个，则返回一个空流，与limit(n)互补
     */
    @Test
    public void test4(){
        employees.stream()
                .filter(employee -> {
                    System.out.println("短路");   // || &&
                    return employee.getSalary()>5000;
                })
                .distinct()
                .limit(2)
                .forEach(System.out::println);
    }
    /**
     *  中间操作
     *  distinct --- 筛选，通过流所生成元素的hashCode()和equals()去重复元素
     */
    /**
     *   映射
     *   map --- 读取Lambda，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个
     *           新的元素；
     *   flatMap --- 接收一个函数作为参数，将流中的每个值换成另一流，然后把所有流连成一个流
     */
     @Test
    public void test5(){
      List<String> list = Arrays.asList("aa","bb","cc","dd");
         list.stream()
                 .map(str ->str.toUpperCase())
                 .forEach(System.out::println);  /* */
         System.out.println("----------------------------------------");
         employees.stream()
                 .map(Employee::getName)
                 .forEach(System.out::println);
         System.out.println("----------------------------------------");
       Stream<Stream<Character>> stream = list.stream()
               .map(TestStreamAPI2::filterCharacter);
       stream.forEach(s->s.forEach(System.out::println));
         System.out.println("----------------------------------------");
       Stream<Character> stream1 = list.stream()
        .flatMap(TestStreamAPI2::filterCharacter);
         stream1.forEach(System.out::println);

     }
     @Test
    public void test6(){
         List<String> list = Arrays.asList("aa","bb","cc","dd");
         List<String> list2 = new ArrayList<>();
         list2.add("tian");
         list2.add("加");
         list2.addAll(list);
         System.out.println(list2);
     }
    /**
     *   解析字符串，每个元素放入集合中
     */
    public static Stream<Character> filterCharacter(String str){
       List<Character> list = new ArrayList<>();
       for(Character ch: str.toCharArray()){
          list.add(ch);
       }
       return list.stream();
    }
    /**
     *   中间操作：排序
     *        sorted()    --- 自然排序,Comparable
     *        sorted(Comparator com)  --- 定制排序,Comparator
     */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("ff","aa","bb","cc","dd");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        employees.stream()
                .sorted((e1,e2)->{
                    if(e1.getAge() == e2.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }else{
                        return String.valueOf(e1.getAge()).compareTo(String.valueOf(e2.getAge()));
                    }
                }).forEach(System.out::println);
    }


}
